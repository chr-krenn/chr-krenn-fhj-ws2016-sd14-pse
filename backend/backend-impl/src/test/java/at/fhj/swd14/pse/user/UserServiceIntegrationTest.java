package at.fhj.swd14.pse.user;

import java.util.List;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import at.fhj.swd14.pse.base.IntegrationTestUtil;
import at.fhj.swd14.pse.database.DatabaseTestUtil;

public class UserServiceIntegrationTest {

	private UserService userService;
	private UserDto userDto;
	
	@Before
    public void setup() throws NamingException {
		userService = IntegrationTestUtil.getService(UserService.class);
		userDto = UserServiceHelper.insertUser(userService);
    }
	
	@Test
	public void testSaveUser() {
		UserDto userDto2 = UserConverter.convert(getUser());
		assertUserDto(userDto, userDto2);
	}
	
	@Test
	public void testFindUser() {
		UserDto userDto2 = userService.find(userDto.getId());
		assertUserDto(userDto, userDto2);
	}
	
	private User getUser() {
		List<User> users = DatabaseTestUtil.getEntityManager().createQuery("SELECT u FROM User u WHERE id=:userid", User.class)
				.setParameter("userid", userDto.getId())
				.getResultList();
		Assert.assertTrue(users.size() == 1);
		return users.get(0);
	}
	
	private void assertUserDto(UserDto user1, UserDto user2) {
		Assert.assertEquals(user1.getId(), user2.getId());
		Assert.assertEquals(user1.getMail(), user2.getMail());
	}
}
