

package at.fhj.swd14.pse.community;

import java.io.Serializable;import javax.persistence.Column;

import javax.persistence.EmbeddedId;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.MapsId;

import javax.persistence.Table;
import at.fhj.swd14.pse.user.User;

@Entity
@Table(name = "user_community")
public class UserCommunity implements Serializable{

    public UserCommunity(User user, Community community, boolean activated){

        this.pk = new UserCommunityPK(user.getId(), community.getId());

        setCommunity(community);

        setUser(user);

        setActivated(activated);

    }

    

    public UserCommunity(){

    }

    private static final long serialVersionUID = 1L;    
    
    @EmbeddedId
    private UserCommunityPK pk;

    @MapsId("user_id")
    @ManyToOne
    @JoinColumn(insertable = true, updatable = true)
    private User user;    
    
    @MapsId("community_id")
    @ManyToOne
    @JoinColumn(insertable = true, updatable = true)
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

        if (this == obj)

            return true;

        if (obj == null)

            return false;

        if (getClass() != obj.getClass())

            return false;

        UserCommunity other = (UserCommunity) obj;

        if (pk == null) {

            if (other.pk != null)

                return false;

        } else if (!pk.equals(other.pk))

            return false;

        return true;

    }    public UserCommunityPK getId() {

        return pk;

    }    public void setId(UserCommunityPK id) {

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

    }}