package at.fhj.swd14.pse.tag;


import at.fhj.swd14.pse.message.Message;
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
    public void testAddMessage(){
        Tag t = new Tag();
        Message m = new Message(2L);
        t.addMessage(m);
        Assert.assertNotNull(t.getMessages());
        Assert.assertEquals(m.getId(), t.getMessages().get(0).getId());
    }
}
