package at.fhj.swd14.pse.message;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import at.fhj.swd14.pse.tag.Tag;
import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.user.User;

public class MessageTest {

	@Test
	public void testConstructor() {

		new Message();
		Long id = 1L;
		Message m = new Message(id);
		Assert.assertEquals(id, m.getId());
	}

	@Test
	public void testToString() {

		Message message = new Message(1L);
		message.setTitle("title-value");
		Assert.assertEquals("Message{id=1, title='title-value'}", message.toString());
	}

	@Test
	public void testCreatedTimestamp() {
		Message message = new Message();
		Assert.assertNull(message.getCreated());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		message.setCreated(timestamp);
		Assert.assertEquals(timestamp, message.getCreated());
	}

	@Test
	public void testModifiedTimestamp() {
		Message message = new Message();
		Assert.assertNull(message.getModified());

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		message.setModified(timestamp);
		Assert.assertEquals(timestamp, message.getModified());
	}

	@Test
	public void testAuthor() {
		User author = new User(100L);
		Message message = new Message();
		message.setAuthor(author);
		Assert.assertNotNull(message.getAuthor());
		Assert.assertEquals(author.getId(), message.getAuthor().getId());
	}

	@Test
	public void testRecipient() {
		User recipient = new User(100L);
		Message message = new Message();
		message.setRecipient(recipient);
		Assert.assertNotNull(message.getRecipient());
		Assert.assertEquals(recipient.getId(), message.getRecipient().getId());
	}

	@Test
	public void testCommunity() {
		Community c = new Community();
		c.setId(100L);
		Message m = new Message();
		m.setCommunity(c);
		Assert.assertNotNull(m.getCommunity());
		Assert.assertEquals(c.getId(), m.getCommunity().getId());
	}

	@Test
	public void testTitle(){
		Message m = new Message();
		m.setTitle("title-value");
		Assert.assertEquals("title-value", m.getTitle());
	}
	
	@Test
	public void testContent() {
		Message m = new Message();
		m.setContent("content-value");
		Assert.assertEquals("content-value", m.getContent());
	}

	@Test
	public void testAddChild() {
		Message message = new Message(1L);

		testAddChildToMessage(message);
	}

	@Test
	public void testAddChildToNullList() {
		Message message = new Message(1L);
		message.setChilds(null);

		testAddChildToMessage(message);
	}

	private void testAddChildToMessage(Message message) {
		Comment comment = new Comment(100L);
		message.addChild(comment);
		Assert.assertEquals(1, message.getChilds().size());
		Assert.assertEquals(comment, message.getChilds().get(0));
		Assert.assertEquals(message, message.getChilds().get(0).getParentMessage());
	}

	@Test
    public void testTag() {

        List<Tag> tagList = new ArrayList();
        tagList.add(new Tag(2L,"eins"));
        tagList.add(new Tag(3L,"zwei"));
        Message m = new Message();
        m.setTags(tagList);
        Assert.assertNotNull(m.getTags());
        for (int i = 0; i<m.getTags().size(); i++) {
            Assert.assertEquals(m.getTags().get(i).getId(), tagList.get(i).getId());
        }
    }

    @Test
    public void testAddTag() {
        Tag t = new Tag(1L, "asdf");
        Message m = new Message();
        m.addTag(t);
        Assert.assertNotNull(m.getTags());
        Assert.assertEquals(t.getId(), m.getTags().get(0).getId());
        Assert.assertEquals(t.getName(), m.getTags().get(0).getName());
    }
}
