package at.fhj.swd14.pse.community;

import java.util.List;

import javax.ejb.Remote;

import at.fhj.swd14.pse.message.MessageDto;

@Remote
public interface CommunityService {

	
	/**
	 * saves or updates the given community and returns the community id.
	 * @param community
	 * @return communityId
	 */
	long save(CommunityDto community);
	
	CommunityDto find(long id);
	
	/**
	 * finds and returns all communities which were created by
	 * the given user.
	 * @param creatorUserId
	 * @return 
	 */
	List<CommunityDto> findByAuthorId(Long creatorUserId);
	
	
	/**
	 * returns communities which are relevant to the user.
	 * Includes own, global and joined community messages
	 * @param userId
	 * @return List of relevant communities
	 */
	List<CommunityDto> findUserRelated(Long userId);
	
}
