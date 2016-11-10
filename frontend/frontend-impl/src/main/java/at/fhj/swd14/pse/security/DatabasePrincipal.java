package at.fhj.swd14.pse.security;

import org.jboss.security.SimplePrincipal;

public class DatabasePrincipal extends SimplePrincipal {
	 
	private static final long serialVersionUID = -2695422673264970748L;
	private Long userId;
	private String salt;
     
    public DatabasePrincipal(String userName) {
        super(userName);
    }
 
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Long getUserId() {
        return userId;
    }
 
    void setUserId(long userId) {
        this.userId = userId;
    }
 
}