package at.fhj.swd14.pse.community;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommunityUserPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
	
    @Basic(optional = false)
    @Column(name = "community_id")
    private int communityId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + communityId;
		result = prime * result + userId;
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
		CommunityUserPK other = (CommunityUserPK) obj;
		if (communityId != other.communityId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
    
    

}
  