package at.fhj.swd14.pse.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DatabasePrincipal that = (DatabasePrincipal) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(userId, that.userId)
                .append(salt, that.salt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(userId)
                .append(salt)
                .toHashCode();
    }
}