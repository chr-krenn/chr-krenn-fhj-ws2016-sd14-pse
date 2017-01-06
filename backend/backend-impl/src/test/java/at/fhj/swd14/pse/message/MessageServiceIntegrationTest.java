package at.fhj.swd14.pse.message;

import java.time.Instant;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd14.pse.BaseIntegrationTest;

public class MessageServiceIntegrationTest extends BaseIntegrationTest{
	@Before
    public void setup() throws NamingException {
		initServices();
		insertUser();
		getCommunity();
    }
	
	@Test
	public void testSaveAndGetMessage(){
		MessageDto message = insertNewMessage();
		
		MessageDto found = messageService.find(message.getId());
		
		compareMessages(message, found);
	}
	
	@Test
	public void testFindByAuthor(){
		ArrayList<MessageDto> messagesBefore = (ArrayList<MessageDto>) messageService.findByAuthorId(user.getId());
		insertNewMessage();
		ArrayList<MessageDto> messagesAfter = (ArrayList<MessageDto>) messageService.findByAuthorId(user.getId());
		Assert.assertEquals(messagesBefore.size() + 1, messagesAfter.size());
	}
	
	@Test
	public void testFindByCommunity(){
		ArrayList<MessageDto> messagesBefore = (ArrayList<MessageDto>) messageService.findByCommunityId(community.getId());
		insertNewMessage();
		ArrayList<MessageDto> messagesAfter = (ArrayList<MessageDto>) messageService.findByCommunityId(community.getId());
		Assert.assertEquals(messagesBefore.size() + 1, messagesAfter.size());
	}
	
	@Test
	public void testFindUserRelated(){
		ArrayList<MessageDto> messagesBefore = (ArrayList<MessageDto>) messageService.findUserRelated(user.getId());
		insertNewMessage();
		ArrayList<MessageDto> messagesAfter = (ArrayList<MessageDto>) messageService.findUserRelated(user.getId());
		Assert.assertEquals(messagesBefore.size() + 1, messagesAfter.size());
	}
	
	@Test
	public void testFindGlobal(){
		ArrayList<MessageDto> messagesBefore = (ArrayList<MessageDto>) messageService.findGlobalMesssages();
		
		MessageDto message = new MessageDto();
		message.setAuthor(user);
		message.setContent("TEST_CONTENT");
		message.setCreated(Instant.now());
		message.setModified(Instant.now());
		message.setTitle("TEST_TITLE");
		long messageId = messageService.save(message);
		message.setId(messageId);
		
		ArrayList<MessageDto> messagesAfter = (ArrayList<MessageDto>) messageService.findGlobalMesssages();
		Assert.assertEquals(messagesBefore.size() + 1, messagesAfter.size());
	}
	
	@Test
	public void testFindPrivate(){
		ArrayList<MessageDto> messagesBefore = (ArrayList<MessageDto>) messageService.findUsersPrivateMessages(user.getId());
		
		MessageDto message = new MessageDto();
		message.setAuthor(user);
		message.setContent("TEST_CONTENT");
		message.setCreated(Instant.now());
		message.setModified(Instant.now());
		message.setTitle("TEST_TITLE");
		message.setRecipient(user);
		long messageId = messageService.save(message);
		message.setId(messageId);
		
		ArrayList<MessageDto> messagesAfter = (ArrayList<MessageDto>) messageService.findUsersPrivateMessages(user.getId());
		Assert.assertEquals(messagesBefore.size() + 1, messagesAfter.size());
	}
	
	private MessageDto insertNewMessage(){
		MessageDto message = new MessageDto();
		message.setAuthor(user);
		message.setCommunity(community);
		message.setContent("TEST_CONTENT");
		message.setCreated(Instant.now());
		message.setModified(Instant.now());
		message.setTitle("TEST_TITLE");
		long messageId = messageService.save(message);
		message.setId(messageId);
		return message;
	}
	
	private void compareMessages(MessageDto expected, MessageDto actual){
		Assert.assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
		Assert.assertEquals(expected.getCommunity().getId(), actual.getCommunity().getId());
		Assert.assertEquals(expected.getContent(), actual.getContent());
		Assert.assertEquals(expected.getTitle(), actual.getTitle());
		Assert.assertEquals(expected.getId(), actual.getId());
	}
}
