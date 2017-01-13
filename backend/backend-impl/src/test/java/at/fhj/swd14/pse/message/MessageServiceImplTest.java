package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.repository.internal.MessageRepositoryImpl;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceImplTest {


    @InjectMocks
    private MessageServiceImpl messageServiceImpl;

    @Mock
    private MessageRepositoryImpl messageRepository;

    @Mock
    private MessageTagHandler messageTagHandler;

    private final Long communityMessageId = 1L;
    private final Long globalMessageId = 2L;
    private final Long privateMessageId = 3L;
    private User author;
    private User recipient;
    private Community community;
    private final List<Message> messages = new ArrayList<>();

    @Before
    public void setup() {

        author = new User(100L);
        recipient = new User(200L);
        community = new Community();
        community.setId(300L);

        List<UserDto> userList = new ArrayList<>();
        userList.add(UserConverter.convert(recipient));

        community.setAllowedUsers(UserConverter.convertToDoList(userList));
        community.setAuthor(author);

        messages.add(MessageTestHelper.getCommunityMessageDummy(communityMessageId, author, community));
        messages.add(MessageTestHelper.getGlobalMessageDummy(globalMessageId, author));
        messages.add(MessageTestHelper.getPrivateMessageDummy(privateMessageId, author, recipient));
    }
    
    @Test
    public void saveTest(){
    	Mockito.doNothing().when(messageRepository).save(messages.get(0));
    	Mockito.doNothing().when(messageTagHandler).handleTags(Mockito.any());
    	Long id = messageServiceImpl.save(MessageConverter.convert(messages.get(0)));
    	Assert.assertEquals(communityMessageId, id);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(messageRepository.find(communityMessageId)).thenReturn(messages.get(0));
        MessageDto dto = messageServiceImpl.find(communityMessageId);

        MessageAssert.assertEquals(MessageConverter.convert(messages.get(0)), dto);
    }


    @Test
    public void findByAuthorTest() {

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", author.getId());

        Mockito.when(messageRepository.executeNamedQuery("Message.findByAuthorId", parameter)).thenReturn(messages);

        List<MessageDto> dtoList = messageServiceImpl.findByAuthorId(author.getId());
        MessageAssert.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(messages)), dtoList);
    }


    @Test
    public void findByCommunityTest() {

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("communityId", community.getId());

        List<Message> expectedMessages = messages.subList(0, 1);

        Mockito.when(messageRepository.executeNamedQuery("Message.findByCommunityId", parameter)).thenReturn(expectedMessages);

        List<MessageDto> dtoList = messageServiceImpl.findByCommunityId(community.getId());
        MessageAssert.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(expectedMessages)), dtoList);
    }

    @Test
    public void findGlobalTest() {
        List<Message> expectedMessages = messages.subList(1, 2);

        Mockito.when(messageRepository.executeNamedQuery("Message.findGlobalMessages")).thenReturn(expectedMessages);

        List<MessageDto> dtoList = messageServiceImpl.findGlobalMesssages();
        MessageAssert.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(expectedMessages)), dtoList);
    }

    @Test
    public void findUsersPrivateTest() {

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", recipient.getId());

        List<Message> expectedMessages = messages.subList(2, 3);

        Mockito.when(messageRepository.executeNamedQuery("Message.findUsersPrivateMessage", parameter)).thenReturn(expectedMessages);

        List<MessageDto> dtoList = messageServiceImpl.findUsersPrivateMessages(recipient.getId());
        MessageAssert.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(expectedMessages)), dtoList);
    }

    @Test
    public void findUserRelatedTest() {

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", recipient.getId());

        Mockito.when(messageRepository.executeNamedQuery("Message.findUserRelated", parameter)).thenReturn(messages);

        List<MessageDto> dtoList = messageServiceImpl.findUserRelated(recipient.getId());
        MessageAssert.assertEquals(new ArrayList<>(MessageConverter.convertToDtoList(messages)), dtoList);
    }

}
