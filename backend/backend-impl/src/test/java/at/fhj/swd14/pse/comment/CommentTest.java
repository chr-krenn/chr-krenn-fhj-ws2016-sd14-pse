package at.fhj.swd14.pse.comment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.user.User;

public class CommentTest
{
	public Comment c;
		
	@Before
	public void setup()
	{
		c = new Comment();
	}
	
	@Test
	public void testCommentConstructor()
	{
		Comment c = new Comment();
		Assert.assertTrue(c instanceof Comment);
	}

	@Test
	public void testCommentConstructor2()
	{
		Long id = 1L;
		Comment c = new Comment(id);
		Assert.assertEquals(id, c.getId());
	}

	@Test
	public void testId()
	{
		Long id = 1L;
		c.setId(id);
		Assert.assertEquals(id, c.getId());
	}

	@Test
	public void testText()
	{
		c.setText("blabla");
		Assert.assertEquals("blabla", c.getText());
	}

	@Test
	public void testAuthor()
	{
		Long id = 1L;
		User user = new User(id);
		c.setAuthor(user);
		Assert.assertEquals(user, c.getAuthor());
	}

	@Test
	public void testParentMessage()
	{
		Long id = 1L;
		Message parentMessage = new Message(id);
		c.setParentMessage(parentMessage);
		Assert.assertEquals(parentMessage, c.getParentMessage());
	}

	@Test
	public void testUsers()
	{
		List<User> users = new ArrayList<>();
		Long id1 = 1L;
		User user1 = new User(id1);
		Long id2 = 1L;
		User user2 = new User(id2);
		
		users.add(user1);
		users.add(user2);
		
		c.setUsers(users);
		
		Assert.assertTrue(c.getUsers().containsAll(users));
	}

	@Test
	public void testGetCreated()
	{
		Assert.assertEquals(null, c.getCreated());
	}

	@Test
	public void testGetModified()
	{
		Assert.assertEquals(null, c.getModified());
	}

	@Test
	public void testToString()
	{	
		User author = new User(1L);
		Message parentMessage = new Message(1L);
		
		c.setId(1L);
		c.setAuthor(author);
		c.setParentMessage(parentMessage);

		String s = "Comment{" +
                "id=" + c.getId() +
                ", userId='" + c.getAuthor() + '\'' +
                ", messageId='" + c.getParentMessage().getId() + '\'' +
                '}';
		
		Assert.assertEquals(s, c.toString());
	}

}
