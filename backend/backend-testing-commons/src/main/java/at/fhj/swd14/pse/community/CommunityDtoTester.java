package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.UserDto;
import org.junit.Assert;

import java.util.List;


public class CommunityDtoTester {

    public static void assertEquals(CommunityDto expected, CommunityDto actual) {

        Assert.assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
        Assert.assertEquals(expected.getCreateTime(), actual.getCreateTime());

        List<UserDto> expectedUserList = expected.getAllowedUsers();
        List<UserDto> actualUserList = actual.getAllowedUsers();

        for (int i = 0; i < expectedUserList.size(); i++) {
            Assert.assertEquals(expectedUserList.get(i).getId(), actualUserList.get(i).getId());
        }

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPublicState(), actual.getPublicState());
    }


}
