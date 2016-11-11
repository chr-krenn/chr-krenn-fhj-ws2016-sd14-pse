package at.fhj.swd14.pse.user;

import org.junit.Assert;

public class UserDtoTester {

    public static void assertEquals(UserDto expected, UserDto actual) {
        Assert.assertEquals(expected.getMail(), actual.getMail());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getSalt(), actual.getSalt());
        Assert.assertEquals(expected.getId(), actual.getId());
    }

}
