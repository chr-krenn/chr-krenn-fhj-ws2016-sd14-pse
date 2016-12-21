package at.fhj.swd14.pse.message;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface MessageService {

    /**
     * saves or updates the given message and returns the message id.
     *
     * @param message
     * @return messageId
     */
    long save(MessageDto message);

    MessageDto find(long id);

    /**
     * finds and returns all messages which were created by
     * the given user.
     *
     * @param creatorUserId
     * @return
     */
    List<MessageDto> findByAuthorId(Long creatorUserId);

    /**
     * finds and returns all messages which belong to the
     * given community.
     *
     * @param communityId
     * @return List of relevant messages
     */
    List<MessageDto> findByCommunityId(Long communityId);

    /**
     * returns messages which are relevant to the user.
     * Includes own, global and joined community messages
     *
     * @param userId
     * @return List of relevant messages
     */
    List<MessageDto> findUserRelated(Long userId);

    /**
     * returns all global messages
     *
     * @return
     */
    List<MessageDto> findGlobalMesssages();

    /**
     * returns all private messages which are directed to the given UserId
     *
     * @param userId Id of the recipient
     * @return private messages
     */
    List<MessageDto> findUsersPrivateMessages(Long userId);
}
