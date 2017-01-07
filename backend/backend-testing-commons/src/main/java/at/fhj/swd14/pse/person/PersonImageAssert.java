package at.fhj.swd14.pse.person;

import org.junit.Assert;

public final class PersonImageAssert {

    private PersonImageAssert() {
    }

    public static void assertEquals(PersonImageDto expected, PersonImageDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertArrayEquals(expected.getData(), actual.getData());
        Assert.assertEquals(expected.getContentType(), actual.getContentType());
        PersonAssert.assertEquals(expected.getPerson(), actual.getPerson());
    }

}
