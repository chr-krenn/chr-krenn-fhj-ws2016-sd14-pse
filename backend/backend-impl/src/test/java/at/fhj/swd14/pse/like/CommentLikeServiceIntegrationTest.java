package at.fhj.swd14.pse.like;

import java.util.ArrayList;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd14.pse.BaseIntegrationTest;
import at.fhj.swd14.pse.base.IntegrationTestUtil;
import org.junit.Assert;

public class CommentLikeServiceIntegrationTest extends BaseIntegrationTest {
	
	private CommentLikeService commentLikeService;
	
	@Before
    public void setup() throws NamingException {
		commentLikeService = IntegrationTestUtil.getService(CommentLikeService.class);
		initServices();
		insertUser();
		getCommunity();
		insertMessage();
		insertCommentForMessage();
    }
	
	@Test
	public void testSaveAndGetCommentLike(){
		int cntBefore = commentLikeService.getLikeCountForComment(comment.getId());
		
		CommentLikeDto cLike = new CommentLikeDto(user, comment);
		commentLikeService.save(cLike);
		
		int cntAfter = commentLikeService.getLikeCountForComment(comment.getId());
		Assert.assertEquals(cntBefore + 1, cntAfter);
	}
	
	@Test
	public void testGetCommentLike(){
		CommentLikeDto cLike = new CommentLikeDto(user, comment);
		commentLikeService.save(cLike);
		
		CommentLikeDto result = commentLikeService.getCommentLike(user.getId(), comment.getId());
		
		Assert.assertEquals(cLike.getLikedComment().getId(), result.getLikedComment().getId());
		Assert.assertEquals(cLike.getLiker().getId(), result.getLiker().getId());
	}
	
	@Test
	public void testGetLikeCountForUserForComments(){
		int cntBefore = commentLikeService.getLikeCountForUserForComments(user.getId());
		
		CommentLikeDto cLike = new CommentLikeDto(user, comment);
		commentLikeService.save(cLike);
		
		int cntAfter = commentLikeService.getLikeCountForUserForComments(user.getId());
		Assert.assertEquals(cntBefore + 1, cntAfter);
	}
	
	@Test
	public void testGetCommentLikes(){
		ArrayList<CommentLikeDto> likesBefore = (ArrayList)commentLikeService.getCommentLikes(comment.getId());
		
		CommentLikeDto cLike = new CommentLikeDto(user, comment);
		commentLikeService.save(cLike);
		
		ArrayList<CommentLikeDto> likesAfter = (ArrayList)commentLikeService.getCommentLikes(comment.getId());
		
		Assert.assertEquals(likesBefore.size() + 1, likesAfter.size());
	}
}
