package at.fhj.swd14.pse.community;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CommunityService {


    /**
     * saves or updates the given community and returns the community id.
     *
     * @param community
     * @return communityId
     */
    long save(CommunityDto community);

    /**
     * removes the given community and returns the community id.
     *
     * @param community
     * @return communityId
     */
    long remove(CommunityDto community);

    CommunityDto find(long id);

    /**
     * finds and returns all communities which were created by
     * the given user.
     *
     * @param creatorUserId
     * @return
     */
    List<CommunityDto> findByAuthorId(Long creatorUserId);


    /**
     * returns communities which are relevant to the user.
     * Includes own, global and joined community messages
     *
     * @param userId
     * @return List of relevant communities
     */
    List<CommunityDto> findUserRelated(Long userId);

    /**
     * returns all communities
     *
     * @param userId
     * @return List of relevant communities
     */
    List<CommunityDto> findAll();
    
    boolean removeUserFromComunity(long communityId, long userId);
    boolean addUserToComunity(long communityId, long userId);


}
