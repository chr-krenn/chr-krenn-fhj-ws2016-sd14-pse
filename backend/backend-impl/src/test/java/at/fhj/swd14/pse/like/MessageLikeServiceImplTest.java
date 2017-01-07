package at.fhj.swd14.pse.like;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageConverter;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.repository.internal.MessageRepositoryImpl;
import at.fhj.swd14.pse.repository.internal.UserRepositoryImpl;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class MessageLikeServiceImplTest {

	@InjectMocks
    private MessageLikeServiceImpl messageLikeService;
	@Mock
    private MessageRepositoryImpl messageRepository;
	@Mock
	private UserRepositoryImpl userRepository;
	
	private MessageDto messageDTO;
	private UserDto userDTO;
	private MessageLikeDto messageLikeDTO;
	private User user;
	private Message message;
	private List<User> users = new ArrayList<User>();
	private static final Long MESSAGEID = 1L;
	private static final Long USERID = 1L;
	
	@Before
    public void setup() {
		user = new User(USERID);
		users.add(user);
		message = new Message(MESSAGEID);
		message.setUsers(users);
		messageDTO = MessageConverter.convert(message);
		userDTO = UserConverter.convert(user);
		messageLikeDTO = new MessageLikeDto(userDTO,messageDTO);
	}
	@Test
	public void saveTest() {
		Mockito.doNothing().when(messageRepository).save(message);
		Mockito.when(messageRepository.find(MESSAGEID)).thenReturn(message);
		messageLikeService.save(messageLikeDTO);
		MessageDto messageDTOFound = MessageConverter.convert(messageRepository.find(MESSAGEID));
		Assert.assertEquals(messageDTOFound.getId(),MESSAGEID);
	}

}
