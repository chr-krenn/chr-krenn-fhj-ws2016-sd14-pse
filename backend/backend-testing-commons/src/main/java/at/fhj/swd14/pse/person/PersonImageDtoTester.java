package at.fhj.swd14.pse.person;

import org.junit.Assert;

public class PersonImageDtoTester {

    public static void assertEquals(PersonImageDto expected, PersonImageDto actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertArrayEquals(expected.getData(), actual.getData());
        Assert.assertEquals(expected.getContentType(), actual.getContentType());
        PersonDtoTester.assertEquals(expected.getPerson(), actual.getPerson());
    }

}
