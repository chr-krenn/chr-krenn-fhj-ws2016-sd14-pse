package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.user.UserAssert;
import org.junit.Assert;

public final class PersonAssert {

    private PersonAssert() {

    }

    public static void assertEquals(final Person expected, final Person actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getFirstname(), actual.getFirstname());
        Assert.assertEquals(expected.getLastname(), actual.getLastname());
        Assert.assertEquals(expected.getImageUrl(), actual.getImageUrl());
        Assert.assertEquals(expected.getPlace(), actual.getPlace());
        StatusAssert.assertEquals(expected.getStatus(), actual.getStatus());
        UserAssert.assertEquals(expected.getUser(), actual.getUser());

        //TODO assert collections
    }

    public static void assertEquals(final PersonDto expected, final PersonDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getFirstname(), actual.getFirstname());
        Assert.assertEquals(expected.getLastname(), actual.getLastname());
        Assert.assertEquals(expected.getImageUrl(), actual.getImageUrl());
        Assert.assertEquals(expected.getPlace(), actual.getPlace());
        StatusAssert.assertEquals(expected.getStatus(), actual.getStatus());
        UserAssert.assertEquals(expected.getUser(), actual.getUser());

        //TODO assert collections
    }
}
