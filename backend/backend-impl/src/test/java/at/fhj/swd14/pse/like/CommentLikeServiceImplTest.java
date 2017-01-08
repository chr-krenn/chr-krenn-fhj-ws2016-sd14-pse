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

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentConverter;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.repository.internal.CommentRepositoryImpl;
import at.fhj.swd14.pse.repository.internal.UserRepositoryImpl;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class CommentLikeServiceImplTest {

	@InjectMocks
    private CommentLikeServiceImpl commentLikeService;
	@Mock
    private CommentRepositoryImpl commentRepository;
	@Mock
	private UserRepositoryImpl userRepository;
	
	private CommentDto commentDTO;
	private UserDto userDTO;
	private CommentLikeDto commentLikeDTO;
	private Comment comment;
	private User user;
	private List<User> users = new ArrayList<User>();
	private List<Comment> comments = new ArrayList<Comment>();
	private final static Long USERID = 1L;
	private final static Long COMMENTID = 1L;
	
	@Before
	public void setup() {
		user = new User(USERID);
		users.add(user);
		comment = new Comment(COMMENTID);
		comments.add(comment);
		comment.setUsers(users);
		user.setComments(comments);
		commentDTO = CommentConverter.convert(comment);
		userDTO = UserConverter.convert(user);
		commentLikeDTO = new CommentLikeDto(userDTO,commentDTO);
	}
	
	@Test
	public void saveFirstTest() {
		Mockito.doNothing().when(commentRepository).save(comment);
		Mockito.when(commentRepository.find(COMMENTID)).thenReturn(comment);
		commentLikeService.save(commentLikeDTO);
		CommentDto commentDTOFound = CommentConverter.convert(commentRepository.find(COMMENTID));
		Assert.assertEquals(commentDTOFound.getId(),COMMENTID);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void saveSecondTest() {
		commentLikeService.save(null);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void saveThirdTest() {
		Mockito.doThrow(Exception.class).when(commentRepository).save(Mockito.any());
		comment = new Comment(100L);
		commentLikeDTO = new CommentLikeDto(userDTO,CommentConverter.convert(comment));
		commentLikeService.save(commentLikeDTO);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void saveFourthTest() {
		Mockito.doThrow(Exception.class).when(commentRepository).find(COMMENTID);
		commentLikeService.save(commentLikeDTO);
	}
	
	@Test
	public void getCommentLikeFirstTest() {
		Mockito.when(commentRepository.find(COMMENTID)).thenReturn(comment);
		commentLikeDTO = commentLikeService.getCommentLike(USERID,COMMENTID);
		Assert.assertEquals(commentLikeDTO.getLiker().getId(),USERID);
		Assert.assertEquals(commentLikeDTO.getLikedComment().getId(),COMMENTID);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void getCommentLikeSecondTest() {
		commentLikeDTO = commentLikeService.getCommentLike(0L,0L);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void getCommentLikeThirdTest() {
		Mockito.doThrow(Exception.class).when(commentRepository).find(100L);
		commentLikeService.getCommentLike(USERID,100L);
	}
	
	@Test
	public void getCommentLikesFirstTest() {
		Mockito.when(commentRepository.find(COMMENTID)).thenReturn(comment);
		List<CommentLikeDto> commentLikeDTOs = commentLikeService.getCommentLikes(COMMENTID);
		Assert.assertEquals(commentLikeDTOs.get(0).getLiker().getId(),USERID);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void getCommentLikesSecondTest() {
		commentLikeService.getCommentLikes(0L);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void getCommentLikesThirdTest() {
		Mockito.doThrow(Exception.class).when(commentRepository).find(100L);
		commentLikeService.getCommentLikes(100L);
	}
	
	@Test
	public void getLikeCountForCommentFirstTest() {
		Mockito.when(commentRepository.find(COMMENTID)).thenReturn(comment);
		int likeCount = commentLikeService.getLikeCountForComment(COMMENTID);
		Assert.assertEquals(likeCount,comment.getUsers().size());
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void getLikeCountForCommentSecondTest() {
		commentLikeService.getLikeCountForComment(COMMENTID);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void getLikeCountForCommentThirdTest() {
		Mockito.doThrow(Exception.class).when(commentRepository).find(100L);
		commentLikeService.getLikeCountForComment(100L);
	}
	
	@Test
	public void getLikeCountForUserForCommentsFirstTest() {
		Mockito.when(userRepository.find(USERID)).thenReturn(user);
		int likeCount = commentLikeService.getLikeCountForUserForComments(USERID);
		Assert.assertEquals(likeCount,user.getComments().size());
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void getLikeCountForUserForCommentsSecondTest() {
		commentLikeService.getLikeCountForUserForComments(USERID);
	}
	
	@Test(expected = CommentLikeServiceException.class)
	public void getLikeCountForUserForCommentsThirdTest() {
		Mockito.doThrow(Exception.class).when(userRepository).find(100L);
		commentLikeService.getLikeCountForUserForComments(100L);
	}
}
