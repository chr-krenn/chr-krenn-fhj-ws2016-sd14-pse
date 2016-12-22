package at.fhj.swd14.pse.message;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.comment.Comment;

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
    public void testAddChild(){
    	Message message = new Message(1L);
    	Comment comment = new Comment(100L);
    	message.addChild(comment);
    	
    	testAddChildToMessage(message, comment);
    }
    
    public void testAddChildToNullList(){
    	Message message = new Message(1L);
    	Comment comment = new Comment(100L);
    	message.setChilds(null);
    	message.addChild(comment);
    	testAddChildToMessage(message, comment);
    }
    
    private void testAddChildToMessage(Message message, Comment comment){
    	
    	Assert.assertEquals(1, message.getChilds().size());
    	Assert.assertEquals(comment, message.getChilds().get(0));
    	Assert.assertEquals(message, message.getChilds().get(0).getParentMessage());
    }
}
