package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;
import org.junit.Before;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CommunityConverterTest {

    Community community;
    CommunityDto communityDto;
    UserDto userDao;
    User user;
    boolean state;
    User user_author;
    UserDto userdto_author;
    Date date = new Date(0);
    String description = "Communitytest";
    String name = "Test Name";

    @Before
    public void setup() {
        this.userdto_author = this.createUserDto((long) 1, "author@test.cu", "password_author", "salt");
        this.user_author = this.createUser((long) 1, "author@test.cu", "password_author", "salt");

        this.createCommunity();
        this.createCommunityDto();
    }

//	@Test
//	public void testCommunityToCommunityDto(){
////		CommunityDto c = CommunityConverter.convert(this.community);
////		CommunityDtoTester.assertEquals(this.communityDto, c);
//	}


    private void createCommunityDto() {
        this.communityDto = new CommunityDto((long) 1);
        this.communityDto.setActiveState(this.state);
        this.communityDto.setAllowedUsers(this.getUserDtoList());
        this.communityDto.setAuthor(this.userdto_author);
        this.communityDto.setCreateTime(this.date);
        this.communityDto.setDescription(this.description);
        this.communityDto.setName(this.name);
        this.communityDto.setPendingUsers(this.getUserDtoList());
        this.communityDto.setPublicState(this.state);

    }

    private void createCommunity() {
//        this.community = new Community();
//        this.community.setId((long) 1);
//        this.community.setAllowedUsers(this.getUserDtoList());
//        this.community.setAuthor(this.user_author);
//        this.community.setName(this.name);
//        this.community.setPublicState(this.state);

    }

    private List<User> getUserList() {

        List<User> user = new ArrayList<>();
        user.add(this.createUser((long) 1, "mail_1@test.cu", "password_1", "salt"));
        user.add(this.createUser((long) 2, "mail_2@test.cu", "password_2", "salt"));

        return user;
    }

    private List<UserDto> getUserDtoList() {

        List<UserDto> userDto = new ArrayList<>();
        userDto.add(this.createUserDto((long) 1, "mail_1@test.cu", "password_1", "salt"));
        userDto.add(this.createUserDto((long) 2, "mail_2@test.cu", "password_2", "salt"));

        return userDto;
    }


    private User createUse(long id, String mail, String password, String salt) {
        User u = new User();
        u.setId(id);
        u.setMail(mail);
        u.setPassword(password);
        u.setSalt(salt);

        return u;
    }

    private UserDto createUserDto(long id, String mail, String password, String salt) {
        UserDto u = new UserDto();
        u.setId(id);
        u.setMail(mail);
        u.setPassword(password);
        u.setSalt(salt);
        return u;
    }

    private User createUser(long id, String mail, String password, String salt) {
        User u = new User(id);
        u.setMail(mail);
        u.setPassword(password);
        u.setSalt(salt);

        return u;
    }
}
