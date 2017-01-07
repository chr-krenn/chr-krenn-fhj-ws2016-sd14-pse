package at.fhj.swd14.pse.person;

import org.junit.Assert;

public final class StatusAssert {

    private StatusAssert() {
    }

    public static void assertEquals(StatusDto expected, StatusDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getName(), actual.getName());
    }

}
