package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.person.PersonAssert;
import org.junit.Assert;

public final class NewsAssert {

    private NewsAssert() {
    }

    public static void assertEquals(final News expected, final News actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getMessage(), actual.getMessage());
        Assert.assertEquals(expected.getActivation(), actual.getActivation());
        Assert.assertEquals(expected.getTermination(), actual.getTermination());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());

        PersonAssert.assertEquals(expected.getAuthor(), actual.getAuthor());
    }

    public static void assertEquals(final NewsDto expected, final NewsDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getMessage(), actual.getMessage());
        Assert.assertEquals(expected.getActivation(), actual.getActivation());
        Assert.assertEquals(expected.getTermination(), actual.getTermination());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());

        PersonAssert.assertEquals(expected.getAuthor(), actual.getAuthor());
    }
}
