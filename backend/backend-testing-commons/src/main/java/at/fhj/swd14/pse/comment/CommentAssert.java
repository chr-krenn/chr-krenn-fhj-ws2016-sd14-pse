package at.fhj.swd14.pse.comment;

import org.junit.Assert;

public final class CommentAssert {

    private CommentAssert() {
    }

    public static void assertEquals(CommentDto expected, CommentDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
        Assert.assertEquals(expected.getAuthor().getMail(), actual.getAuthor().getMail());
        Assert.assertEquals(expected.getAuthor().getPassword(), actual.getAuthor().getPassword());
        Assert.assertEquals(expected.getAuthor().getSalt(), actual.getAuthor().getSalt());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getParentMessage(), actual.getParentMessage());
        Assert.assertEquals(expected.getText(), actual.getText());
    }
}
