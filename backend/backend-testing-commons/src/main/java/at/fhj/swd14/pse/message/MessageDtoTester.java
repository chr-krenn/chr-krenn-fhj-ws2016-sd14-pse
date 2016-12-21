package at.fhj.swd14.pse.message;

import org.junit.Assert;

import java.util.List;

public class MessageDtoTester {

    public static void assertEquals(MessageDto expected, MessageDto actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());

        // recipient is nullable
        if (expected.getRecipient() != null) {
            Assert.assertNotNull(actual.getRecipient());
            Assert.assertEquals(expected.getRecipient().getId(), actual.getRecipient().getId());
        } else {
            Assert.assertNull(actual.getRecipient());
        }

        // community is nullable
        if (expected.getCommunity() != null) {
            Assert.assertNotNull(actual.getCommunity());
            Assert.assertEquals(expected.getCommunity().getId(), actual.getCommunity().getId());
        } else {
            Assert.assertNull(actual.getCommunity());
        }

        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getChilds().size(), actual.getChilds().size());

        for (int i = 0; i < expected.getChilds().size(); i++) {
            Assert.assertEquals(expected.getChilds().get(i).getId(), actual.getChilds().get(i).getId());
        }
    }

    public static void assertEquals(List<MessageDto> expected, List<MessageDto> actual) {

        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++)
            assertEquals(expected.get(i), actual.get(i));
    }

}
