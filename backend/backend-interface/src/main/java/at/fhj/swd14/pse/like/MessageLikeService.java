package at.fhj.swd14.pse.like;

import javax.ejb.Remote;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.message.MessageDto;
import java.util.List;

@Remote
public interface MessageLikeService {

	public void save(MessageLikeDto messageLike);
	
	public MessageLikeDto getMessageLike(UserDto user,MessageDto message);
	
	public List<MessageLikeDto> getMessageLikes(MessageDto message);
	
	public int getLikeCountForMessage(MessageDto message);
	
	public int getLikeCountForUserForMessages(UserDto user);
}
