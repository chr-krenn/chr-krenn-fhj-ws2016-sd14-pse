package at.fhj.swd14.pse.person;

import org.junit.Assert;

public final class AbstractPersonInformationAssert {

    private AbstractPersonInformationAssert() {
    }

    public static void assertEquals(AbstractPersonInformationDto expected, AbstractPersonInformationDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getValue(), actual.getValue());
    }

}
