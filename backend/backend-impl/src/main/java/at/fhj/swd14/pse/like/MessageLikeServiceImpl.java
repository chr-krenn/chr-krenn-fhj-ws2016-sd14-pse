package at.fhj.swd14.pse.like;

import java.util.List;

import javax.ejb.EJB;

import at.fhj.swd14.pse.converter.MessageConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.repository.MessageRepository;
import at.fhj.swd14.pse.repository.UserRepository;
import at.fhj.swd14.pse.user.UserDto;

public class MessageLikeServiceImpl implements MessageLikeService {

	@EJB
    private MessageRepository messageRepository;
	@EJB
	private UserRepository userRepository;
	
	@Override
	public void save(MessageLikeDto messageLike) {
		MessageDto messageDTO = messageLike.getLikedMessage();
		UserDto userDTO = messageLike.getLiker();
		messageRepository.save(MessageConverter.convert(messageDTO));
		userRepository.save(UserConverter.convert(userDTO));

	}

	@Override
	public MessageLikeDto getMessageLike(long userId, long messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageLikeDto> getMessageLikes(long messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLikeCountForMessage(long messageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLikeCountForUserForMessages(long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
