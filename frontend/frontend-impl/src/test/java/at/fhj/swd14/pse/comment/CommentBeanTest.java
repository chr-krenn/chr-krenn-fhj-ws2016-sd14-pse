package at.fhj.swd14.pse.comment;

import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.message.MessageService;
import at.fhj.swd14.pse.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class CommentBeanTest
{
	@InjectMocks
	private CommentBean commentBean;
	
	@Mock
	private MessageService messageService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private CommentService commentService;

	@Mock
    private FacesContext context;
	
	@Before
	public void setUp()
	{
		
	}

	@Test
	public void testSetGetComment()
	{	
		CommentDto comment = new CommentDto(1L);
		comment.setText("blabla");
		
		commentBean.setComment(comment );
		
		Assert.assertEquals(comment, commentBean.getComment());
	}

}
