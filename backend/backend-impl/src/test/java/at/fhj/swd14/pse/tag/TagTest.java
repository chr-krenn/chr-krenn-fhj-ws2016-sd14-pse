package at.fhj.swd14.pse.tag;


import at.fhj.swd14.pse.message.Message;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TagTest {

    @Test
    public void testConstructor() {
        Tag t = new Tag();
    }

    @Test
    public void testToString() {

        Tag t = new Tag(1L, "valid");
        Assert.assertEquals("Tag{id=1, name='valid'}", t.toString());
    }

    @Test
    public void testSetMessages(){
        Tag t = new Tag();
        List<Message> messages = new ArrayList<>();
        
        Message m = new Message(2L);
        messages.add(m);
        
        t.setMessages(messages);
        
        Assert.assertNotNull(t.getMessages());
        Assert.assertEquals(m.getId(), t.getMessages().get(0).getId());
    }
    
    @Test
    public void testAddMessage(){
        Tag t = new Tag();
        Message m = new Message(2L);
        Message m2 = new Message(3L);
        t.addMessage(m);
        t.addMessage(m2);
        Assert.assertNotNull(t.getMessages());
        Assert.assertEquals(m.getId(), t.getMessages().get(0).getId());
        Assert.assertEquals(m2.getId(), t.getMessages().get(1).getId());
    }
}
