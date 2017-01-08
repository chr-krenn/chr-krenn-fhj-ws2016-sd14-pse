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
	private List<Message> messages = new ArrayList<Message>();
	private static final Long MESSAGEID = 1L;
	private static final Long USERID = 1L;
	
	@Before
    public void setup() {
		user = new User(USERID);
		users.add(user);
		message = new Message(MESSAGEID);
		messages.add(message);
		message.setUsers(users);
		user.setMessages(messages);
		messageDTO = MessageConverter.convert(message);
		userDTO = UserConverter.convert(user);
		messageLikeDTO = new MessageLikeDto(userDTO,messageDTO);
	}
	
	@Test
	public void saveFirstTest() {
		Mockito.doNothing().when(messageRepository).save(message);
		Mockito.when(messageRepository.find(MESSAGEID)).thenReturn(message);
		messageLikeService.save(messageLikeDTO);
		MessageDto messageDTOFound = MessageConverter.convert(messageRepository.find(MESSAGEID));
		Assert.assertEquals(messageDTOFound.getId(),MESSAGEID);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void saveSecondTest() {
		messageLikeService.save(null);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void saveThirdTest() {
		Mockito.doThrow(Exception.class).when(messageRepository).save(Mockito.any());
		message = new Message(100L);
		messageLikeDTO = new MessageLikeDto(userDTO,MessageConverter.convert(message));
		messageLikeService.save(messageLikeDTO);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void saveFourthTest() {
		Mockito.doThrow(Exception.class).when(messageRepository).find(MESSAGEID);
		messageLikeService.save(messageLikeDTO);
	}
	
	@Test
	public void getMessageLikeFirstTest() {
		Mockito.when(messageRepository.find(MESSAGEID)).thenReturn(message);
		messageLikeDTO = messageLikeService.getMessageLike(USERID,MESSAGEID);
		Assert.assertEquals(messageLikeDTO.getLiker().getId(),USERID);
		Assert.assertEquals(messageLikeDTO.getLikedMessage().getId(),MESSAGEID);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void getMessageLikeSecondTest() {
		messageLikeDTO = messageLikeService.getMessageLike(0L,0L);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void getMessageLikeThirdTest() {
		Mockito.doThrow(Exception.class).when(messageRepository).find(100L);
		messageLikeDTO = messageLikeService.getMessageLike(USERID,100L);
	}
	
	@Test
	public void getMessageLikeFourthTest() {
		users.clear();
		User user1 = new User(3L);
		users.add(user1);
		message.setUsers(users);
		Mockito.when(messageRepository.find(MESSAGEID)).thenReturn(message);
		messageLikeDTO = messageLikeService.getMessageLike(USERID,MESSAGEID);
		Assert.assertNull(messageLikeDTO.getLiker());
	}
	
	@Test
	public void getMessageLikeFifthTest() {
		Mockito.when(messageRepository.find(MESSAGEID)).thenReturn(message);
		messageLikeDTO = messageLikeService.getMessageLike(USERID,MESSAGEID);
		Assert.assertEquals(messageLikeDTO.getLiker().getId(),USERID);
	}
	
	@Test
	public void getMessageLikesFirstTest() {
		Mockito.when(messageRepository.find(MESSAGEID)).thenReturn(message);
		List<MessageLikeDto> messageLikeDTOs = messageLikeService.getMessageLikes(MESSAGEID);
		Assert.assertEquals(messageLikeDTOs.get(0).getLiker().getId(),USERID);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void getMessageLikesSecondTest() {
		messageLikeService.getMessageLikes(0L);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void getMessageLikesThirdTest() {
		Mockito.doThrow(Exception.class).when(messageRepository).find(100L);
		messageLikeService.getMessageLikes(100L);
	}
	
	@Test
	public void getLikeCountForMessageFirstTest() {
		Mockito.when(messageRepository.find(MESSAGEID)).thenReturn(message);
		int likeCount = messageLikeService.getLikeCountForMessage(MESSAGEID);
		Assert.assertEquals(likeCount,message.getUsers().size());
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void getLikeCountForMessageSecondTest() {
		messageLikeService.getLikeCountForMessage(MESSAGEID);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void getLikeCountForMessageThirdTest() {
		Mockito.doThrow(Exception.class).when(messageRepository).find(100L);
		messageLikeService.getLikeCountForMessage(100L);
	}
	
	@Test
	public void getLikeCountForUserForMessagesFirstTest() {
		Mockito.when(userRepository.find(USERID)).thenReturn(user);
		int likeCount = messageLikeService.getLikeCountForUserForMessages(USERID);
		Assert.assertEquals(likeCount,user.getMessages().size());
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void getLikeCountForUserForMessagesSecondTest() {
		messageLikeService.getLikeCountForUserForMessages(USERID);
	}
	
	@Test(expected = MessageLikeServiceException.class)
	public void getLikeCountForUserForMessagesThirdTest() {
		Mockito.doThrow(Exception.class).when(userRepository).find(100L);
		messageLikeService.getLikeCountForUserForMessages(100L);
	}
}
