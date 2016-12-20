package at.fhj.swd14.pse.like;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MessageLikeService {
	/**
	 * Saves a message like data transfer object.
	 * @param messageLike The message like data transfer object.
	 */
    void save(MessageLikeDto messageLike);
	/**
	 * Returns the message like data transfer object for a given user and message.
	 * @param userId The id of the user.
	 * @param messageId The id of the message.
	 * @return The data transfer object of a message like.
	 */
    MessageLikeDto getMessageLike(long userId, long messageId);
	/**
	 * Returns the message like data transfer objects for a message.
	 * @param messageId The id of the message.
	 * @return The list of message like data transfer objects.
	 */
    List<MessageLikeDto> getMessageLikes(long messageId);
	/**
	 * Returns the number of likes for a message.
	 * @param messageId The id of the message
	 * @return The count of the likes for a message.
	 */
    int getLikeCountForMessage(long messageId);
	/***
	 * Returns the number of likes of a user of messages.
	 * @param userId The id of the user.
	 * @return The number of likes of a user of messages.
	 */
    int getLikeCountForUserForMessages(long userId);
}
