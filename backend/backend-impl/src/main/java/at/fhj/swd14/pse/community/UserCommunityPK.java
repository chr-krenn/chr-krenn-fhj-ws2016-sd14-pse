

package at.fhj.swd14.pse.community;

import java.io.Serializable;
import javax.persistence.Embeddable;
import at.fhj.swd14.pse.user.User;

@Embeddable
public class UserCommunityPK implements Serializable {    
	private static final long serialVersionUID = 1L;    
	public UserCommunityPK(Long userId, Long communityId) {
		user_id = userId;
		community_id = communityId;

    }  
	
	public UserCommunityPK() {    
    	
    } 
	
    private Long user_id;    
    private Long community_id;    
    
    @Override
    public int hashCode() {
        
        return (int) (community_id + user_id);
    }    
    
    @Override
    public boolean equals(Object obj) {

        if (this == obj)

            return true;

        if (obj == null)

            return false;

        if (getClass() != obj.getClass())

            return false;

           
        return true;

    }}