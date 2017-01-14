package at.fhj.swd14.pse.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.repository.internal.CommentRepositoryImpl;
import at.fhj.swd14.pse.user.User;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest
{
	@InjectMocks
	private CommentServiceImpl commentServiceImpl;

	@Mock
	private CommentRepositoryImpl commentRepository;

	private User user;
	private Message message;
	private Comment comment;
	private Community community;
	private List<Comment> comments;
	private final Long commentId = 1L;
	private final Long messageId = 2L;
	private final Long userId = 3L;
	private final Long communityId = 4L;

	@Before
	public void setup()
	{
		user = new User();
		user.setId(userId);
		user.setMail("mail@nerdistan.io");

		community = new Community();
		community.setId(communityId);
		
		message = new Message(messageId);		
		message.setCommunity(community);
		
		comment = new Comment(commentId);
		comment.setAuthor(user);
		comment.setText("test-comment");
		comment.setParentMessage(message);

		comments = new ArrayList<>();
		comments.add(comment);
	}

	@Test
	public void testFind()
	{
		Mockito.when(commentRepository.find(commentId)).thenReturn(comments.get(0));
		CommentDto dto = commentServiceImpl.find(commentId);

		CommentAssert.assertEquals(CommentConverter.convert(comments.get(0)), dto);
	}

	@Test
	public void testSave()
	{
		Long id = commentServiceImpl.save(CommentConverter.convert(comments.get(0)));

		Assert.assertEquals(commentId, id);
	}

	@Test
	public void testFindByAuthor()
	{
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("authorUserId", user.getId());

		Mockito.when(commentRepository.executeNamedQuery("Comment.findByAuthorId", parameter)).thenReturn(comments);

		List<CommentDto> dtoList = commentServiceImpl.findByAuthorId(user.getId());

		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getId(), dtoList.get(0).getId());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getText(), dtoList.get(0).getText());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getAuthor().getId(), dtoList.get(0).getAuthor().getId());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getAuthor().getMail(), dtoList.get(0).getAuthor().getMail());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getAuthor().getPassword(), dtoList.get(0).getAuthor().getPassword());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getAuthor().getSalt(), dtoList.get(0).getAuthor().getSalt());
	}

	@Test
	public void testFindByMessageId()
	{
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("commentId", communityId);

		Mockito.when(commentRepository.executeNamedQuery("Comment.findByCommunityId", parameter)).thenReturn(comments);

		List<CommentDto> dtoList = commentServiceImpl.findByMessageId(communityId);
		
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getId(), dtoList.get(0).getId());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getText(), dtoList.get(0).getText());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getAuthor().getId(), dtoList.get(0).getAuthor().getId());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getAuthor().getMail(), dtoList.get(0).getAuthor().getMail());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getAuthor().getPassword(), dtoList.get(0).getAuthor().getPassword());
		Assert.assertEquals(CommentConverter.convert(comments.get(0)).getAuthor().getSalt(), dtoList.get(0).getAuthor().getSalt());
	}
	
}
