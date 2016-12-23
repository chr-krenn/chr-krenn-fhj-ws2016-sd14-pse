package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_community")
public class UserCommunity implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserCommunity(User user, Community community, boolean activated) {
        this.pk = new UserCommunityPK(user.getId(), community.getId());
        setCommunity(community);
        setUser(user);
        setActivated(activated);
    }

    public UserCommunity() {
    }

    @EmbeddedId
    private UserCommunityPK pk;

    @MapsId("user_id")
    @ManyToOne
    @JoinColumn
    private User user;

    @MapsId("community_id")
    @ManyToOne
    @JoinColumn
    private Community community;

    @Override
    public int hashCode() {

        final int prime = 31;

        int result = 1;

        result = prime * result + ((pk == null) ? 0 : pk.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }
        UserCommunity other = (UserCommunity) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }
        return true;

    }

    public UserCommunityPK getId() {
        return pk;
    }

    public void setId(UserCommunityPK id) {
        this.pk = id;
    }

    @Column(name = "activated")
    private boolean activated;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}