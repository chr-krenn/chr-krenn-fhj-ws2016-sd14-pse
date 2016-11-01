package at.fhj.swd14.pse.message;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MessageService {
	
	/**
	 * saves or updates the given message and returns the message id.
	 * @param message
	 * @return messageId
	 */
	long save(MessageDto message);
	
	MessageDto find(long id);
	
	/**
	 * finds and returns all messages which were created by
	 * the given user.
	 * @param creatorUserId
	 * @return 
	 */
	List<MessageDto> findByCreatorId(Long creatorUserId);
	
	/**
	 * finds and returns all messages which belong to the
	 * given community.
	 * @param communityId
	 * @return List of relevant messages
	 */
	List<MessageDto> findByCommunityId(Long communityId);
	
	/**
	 * returns messages which are relevant to the user.
	 * Includes own, global and joined community messages
	 * @param userId
	 * @return List of relevant messages
	 */
	List<MessageDto> findUserRelated(Long userId);
}
