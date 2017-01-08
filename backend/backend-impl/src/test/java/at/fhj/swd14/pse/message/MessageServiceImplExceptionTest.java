package at.fhj.swd14.pse.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.repository.internal.MessageRepositoryImpl;
import at.fhj.swd14.pse.tag.TagDto;
import at.fhj.swd14.pse.tag.TagServiceException;
import at.fhj.swd14.pse.user.User;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceImplExceptionTest {

	
	@InjectMocks
    private MessageServiceImpl messageServiceImpl;

    @Mock
    private MessageRepositoryImpl messageRepository;
    
    @Spy 
    private MessageTagHandler msgTagHandler;


	@Test(expected=MessageServiceException.class)
	public void testSaveMessageNull() {
    	messageServiceImpl.save(null);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testSaveMessageRepoException(){
		Mockito.doThrow(Exception.class).when(messageRepository).save(Mockito.any());
		
		User author = new User(100L);
		MessageDto messageDto = MessageConverter.convert(MessageTestHelper.getGlobalMessageDummy(1L, author));
		messageServiceImpl.save(messageDto);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testHandleMessageTagsException(){
		
		User author = new User(100L);
		MessageDto messageDto = MessageConverter.convert(MessageTestHelper.getGlobalMessageDummy(1L, author));
		Mockito.doThrow(new MessageTagHandlerException("Error tag handling")).when(msgTagHandler).handleTags(Mockito.any());
//		Mockito.doThrow(new VerificationException("TagDto was null")).when(tagService).save(Matchers.any(TagDto.class));  

		messageServiceImpl.save(messageDto); 
		
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindByIdRepoException(){
		Mockito.doThrow(Exception.class).when(messageRepository).find(Mockito.anyLong());
		messageServiceImpl.find(1L);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindByAuthorIdNull(){
		messageServiceImpl.findByAuthorId(null);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindByAuthorIdRepoException(){
		Mockito.doThrow(Exception.class).when(messageRepository)
										.executeNamedQuery(Mockito.anyString(), Mockito.anyMapOf(String.class, Object.class));
		messageServiceImpl.findByAuthorId(1L);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindByCommunityIdNull(){
		messageServiceImpl.findByCommunityId(null);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindByCommunityIdRepoException(){
		Mockito.doThrow(Exception.class).when(messageRepository)
										.executeNamedQuery(Mockito.anyString(), Mockito.anyMapOf(String.class, Object.class));
		messageServiceImpl.findByCommunityId(1L);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindUserRelatedNull(){
		messageServiceImpl.findUserRelated(null);
	}
	
	
	@Test(expected=MessageServiceException.class)
	public void testFindUserRelatedRepoException(){
		Mockito.doThrow(Exception.class).when(messageRepository)
		.executeNamedQuery(Mockito.anyString(), Mockito.anyMapOf(String.class, Object.class));
		messageServiceImpl.findUserRelated(1L);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindGlobalRepoException(){
		Mockito.doThrow(Exception.class).when(messageRepository)
		.executeNamedQuery(Mockito.anyString());
		messageServiceImpl.findGlobalMesssages();
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindPrivateNull(){
		messageServiceImpl.findUsersPrivateMessages(null);
	}
	
	@Test(expected=MessageServiceException.class)
	public void testFindPrivateRepoExcpetion(){
		Mockito.doThrow(Exception.class).when(messageRepository)
		.executeNamedQuery(Mockito.anyString(), Mockito.anyMapOf(String.class, Object.class));
		messageServiceImpl.findUsersPrivateMessages(1L);
	}
	
}
