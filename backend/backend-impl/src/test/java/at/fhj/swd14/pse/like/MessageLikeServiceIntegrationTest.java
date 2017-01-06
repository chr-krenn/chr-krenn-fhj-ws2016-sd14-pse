package at.fhj.swd14.pse.like;

import java.util.ArrayList;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd14.pse.BaseIntegrationTest;
import at.fhj.swd14.pse.base.IntegrationTestUtil;
import org.junit.Assert;

public class MessageLikeServiceIntegrationTest extends BaseIntegrationTest {
	
	private MessageLikeService messageLikeService;
	
	@Before
    public void setup() throws NamingException {
		messageLikeService = IntegrationTestUtil.getService(MessageLikeService.class);
		initServices();
		insertUser();
		getCommunity();
		insertMessage();
    }
	
	@Test
	public void testSaveAndGetMessageLike(){
		int cntBefore = messageLikeService.getLikeCountForMessage(message.getId());
		
		MessageLikeDto mLike = new MessageLikeDto(user, message);
		messageLikeService.save(mLike);
		
		int cntAfter = messageLikeService.getLikeCountForMessage(message.getId());
		Assert.assertEquals(cntBefore + 1, cntAfter);
	}
	
	@Test
	public void testGetMessageLike(){
		MessageLikeDto cLike = new MessageLikeDto(user, message);
		messageLikeService.save(cLike);
		
		MessageLikeDto result = messageLikeService.getMessageLike(user.getId(), message.getId());
		
		Assert.assertEquals(cLike.getLikedMessage().getId(), result.getLikedMessage().getId());
		Assert.assertEquals(cLike.getLiker().getId(), result.getLiker().getId());
	}
	
	@Test
	public void testGetLikeCountForUserForMessages(){
		int cntBefore = messageLikeService.getLikeCountForUserForMessages(user.getId());
		
		MessageLikeDto cLike = new MessageLikeDto(user, message);
		messageLikeService.save(cLike);
		
		int cntAfter = messageLikeService.getLikeCountForUserForMessages(user.getId());
		Assert.assertEquals(cntBefore + 1, cntAfter);
	}
	
	@Test
	public void testGetMessageLikes(){
		ArrayList<MessageLikeDto> likesBefore = (ArrayList<MessageLikeDto>) messageLikeService.getMessageLikes(message.getId());
		
		MessageLikeDto cLike = new MessageLikeDto(user, message);
		messageLikeService.save(cLike);
		
		ArrayList<MessageLikeDto> likesAfter = (ArrayList<MessageLikeDto>) messageLikeService.getMessageLikes(message.getId());
		
		Assert.assertEquals(likesBefore.size() + 1, likesAfter.size());
	}
}
