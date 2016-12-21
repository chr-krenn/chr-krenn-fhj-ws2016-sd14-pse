package at.fhj.swd14.pse.tag;


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
}
