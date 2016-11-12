package at.fhj.swd14.pse.community;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import at.fhj.swd14.pse.user.User;

@Entity
@Table(name = "community_user")
public class CommunityUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
	 @EmbeddedId
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private CommunityUserPK id;
	
	 @ManyToOne
	    @JoinColumn(name = "community_id", columnDefinition = "idcrew")
	    @MapsId("communityIdcommunity")
	    private Community community;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    @MapsId("userIduser")
	    private User user;

	
}
