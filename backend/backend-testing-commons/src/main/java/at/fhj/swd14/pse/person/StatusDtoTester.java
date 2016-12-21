package at.fhj.swd14.pse.person;

import org.junit.Assert;

public class StatusDtoTester {

    private StatusDtoTester() {
        //gets rid of the default constructor
    }

    public static void assertEquals(StatusDto expected, StatusDto actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
    }

}
