package at.fhj.swd14.pse.user;

import org.junit.Assert;

public final class UserAssert {

    private UserAssert() {

    }

    public static void assertEquals(final User expected, final User actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMail(), actual.getMail());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getSalt(), actual.getSalt());

        //TODO assert collections
    }

    public static void assertEquals(final UserDto expected, final UserDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMail(), actual.getMail());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getSalt(), actual.getSalt());

        //TODO assert collections
    }
}
