package at.fhj.swd14.pse.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.jboss.security.PicketBoxLogger;
import org.jboss.security.PicketBoxMessages;
import org.jboss.security.auth.spi.DatabaseServerLoginModule;

public class SecureDatabaseServerLoginModule extends DatabaseServerLoginModule {

	@Override
	protected boolean validatePassword(String inputPassword, String expectedPassword) {
		if (inputPassword == null || expectedPassword == null)
			return false;

		DatabasePrincipal principle = (DatabasePrincipal) getIdentity();
		String encodedPassword = PasswordHasher.getInstance().hash(inputPassword, principle.getSalt());
		return encodedPassword.equals(expectedPassword);
	}

	/**
	 * Get the expected password for the current username available via the
	 * getUsername() method. This is called from within the login() method after
	 * the CallbackHandler has returned the username and candidate password.
	 * 
	 * @return the valid password String
	 */
	protected String getUsersPassword() throws LoginException {
		String username = getUsername();
		String password;
		String salt;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Transaction tx = null;
		if (suspendResume) {
			// tx = TransactionDemarcationSupport.suspendAnyTransaction();
			try {
				if (tm == null)
					throw PicketBoxMessages.MESSAGES.invalidNullTransactionManager();
				tx = tm.suspend();
			} catch (SystemException e) {
				throw new UnsupportedOperationException(e);
			}
		}

		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(dsJndiName);
			conn = ds.getConnection();
			// Get the password
			PicketBoxLogger.LOGGER.traceExecuteQuery(principalsQuery, username);
			ps = conn.prepareStatement(principalsQuery);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw PicketBoxMessages.MESSAGES.noMatchingUsernameFoundInPrincipals();
			}

			Long userId = rs.getLong(1);
			password = rs.getString(2);
			salt = rs.getString(3);

			// Set the tenant and user properties on the custom principal
			// there maybe a better place to do this, but this is the only place
			// I can see it working without having to hit the database with
			// another query.
			DatabasePrincipal principle = (DatabasePrincipal) getIdentity();
			principle.setUserId(userId);
			principle.setSalt(salt);
			password = convertRawPassword(password);
		} catch (NamingException ex) {
			LoginException le = new LoginException(
					PicketBoxMessages.MESSAGES.failedToLookupDataSourceMessage(dsJndiName));
			le.initCause(ex);
			throw le;
		} catch (SQLException ex) {
			LoginException le = new LoginException(PicketBoxMessages.MESSAGES.failedToProcessQueryMessage());
			le.initCause(ex);
			throw le;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					PicketBoxLogger.LOGGER.warn(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					PicketBoxLogger.LOGGER.warn(e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					PicketBoxLogger.LOGGER.warn(ex);
				}
			}
			if (suspendResume) {
				// TransactionDemarcationSupport.resumeAnyTransaction(tx);
				try {
					tm.resume(tx);
				} catch (Exception e) {
					PicketBoxLogger.LOGGER.warn(e);
				}
			}
		}
		return password;
	}

}
