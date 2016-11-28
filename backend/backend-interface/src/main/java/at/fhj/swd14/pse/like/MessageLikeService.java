package at.fhj.swd14.pse.like;

import javax.ejb.Remote;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.message.MessageDto;
import java.util.List;

@Remote
public interface MessageLikeService {
	/**
	 * Saves a message like data transfer object.
	 * @param messageLike The message like data transfer object.
	 */
	public void save(MessageLikeDto messageLike);
	/**
	 * Returns the message like data transfer object for a given user and message.
	 * @param userId The id of the user.
	 * @param messageId The id of the message.
	 * @return The data transfer object of a message like.
	 */
	public MessageLikeDto getMessageLike(long userId,long messageId);
	/**
	 * Returns the message like data transfer objects for a message.
	 * @param messageId The id of the message.
	 * @return The list of message like data transfer objects.
	 */
	public List<MessageLikeDto> getMessageLikes(long messageId);
	/**
	 * Returns the number of likes for a message.
	 * @param messageId The id of the message
	 * @return The count of the likes for a message.
	 */
	public int getLikeCountForMessage(long messageId);
	/***
	 * Returns the number of likes of a user of messages.
	 * @param userId The id of the user.
	 * @return The number of likes of a user of messages.
	 */
	public int getLikeCountForUserForMessages(long userId);
}
