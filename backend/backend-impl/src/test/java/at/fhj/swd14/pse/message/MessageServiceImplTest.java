package at.fhj.swd14.pse.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.converter.MessageConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.repository.MessageRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceImplTest {

	
	@InjectMocks
	private MessageServiceImpl messageServiceImpl;

	@Mock
	private MessageRepository messageRepository;

	private final Long communityMessageId = 1L;
	private final Long globalMessageId = 2L;
	private final Long privateMessageId = 3L;
	private User author;
	private User recipient;
	private Community community;
	private List<Message> messages = new ArrayList<>();
	
	@Before
	public void setup(){
		
		author = new User(100L);
		recipient = new User(200L);
		community = new Community();
		community.setId(300L);
		
		List<UserDto> userList = new ArrayList<>();
		userList.add(UserConverter.convert(recipient));
		
		community.setAllowedUsers(userList);
		community.setAuthor(author);
		
		messages.add(MessageTestHelper.getCommunityMessageDummy(communityMessageId, author, community));
		messages.add(MessageTestHelper.getGlobalMessageDummy(globalMessageId, author));
		messages.add(MessageTestHelper.getPrivateMessageDummy(privateMessageId, author, recipient));
	}
	
	@Test
	public void findByIdTest(){
		Mockito.when(messageRepository.find(communityMessageId)).thenReturn(messages.get(0));
		MessageDto dto = messageServiceImpl.find(communityMessageId);

		MessageDtoTester.assertEquals(MessageConverter.convert(messages.get(0)), dto);
	}
	
	@Test
	public void findByAuthorTest(){
		
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("authorUserId", author.getId());
		
		Mockito.when(messageRepository.executeNamedQuery("Message.findByAuthorId", parameter)).thenReturn(messages);
		
		List<MessageDto> dtoList = messageServiceImpl.findByAuthorId(author.getId());
		MessageDtoTester.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(messages)), dtoList);
	}
	
	@Test
	public void findByCommunityTest(){
		
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("communityId", community.getId());
		
		List<Message> expectedMessages = messages.subList(0, 1);
		
		Mockito.when(messageRepository.executeNamedQuery("Message.findByCommunityId", parameter)).thenReturn(expectedMessages);
		
		List<MessageDto> dtoList = messageServiceImpl.findByCommunityId(community.getId());
		MessageDtoTester.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(expectedMessages)), dtoList);
	}
	
	@Test
	public void findGlobalTest(){
		List<Message> expectedMessages = messages.subList(1, 2);
		
		Mockito.when(messageRepository.executeNamedQuery("Message.findGlobalMessages")).thenReturn(expectedMessages);
		
		List<MessageDto> dtoList = messageServiceImpl.findGlobalMesssages();
		MessageDtoTester.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(expectedMessages)), dtoList);
	}
	
	@Test
	public void findUsersPrivateTest(){
		
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("userId", recipient.getId());
		
		List<Message> expectedMessages = messages.subList(2, 3);
		
		Mockito.when(messageRepository.executeNamedQuery("Message.findUsersPrivateMessage", parameter)).thenReturn(expectedMessages);
		
		List<MessageDto> dtoList = messageServiceImpl.findUsersPrivateMessages(recipient.getId());
		MessageDtoTester.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(expectedMessages)), dtoList);
	}
	
	@Test
	public void findUserRelatedTest(){
		
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("userId", recipient.getId());
		
		Mockito.when(messageRepository.executeNamedQuery("Message.findUserRelated", parameter)).thenReturn(messages);
		
		List<MessageDto> dtoList = messageServiceImpl.findUserRelated(recipient.getId());
		MessageDtoTester.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(messages)), dtoList);
	}
	
}
