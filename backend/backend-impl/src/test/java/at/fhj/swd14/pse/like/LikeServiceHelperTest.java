package at.fhj.swd14.pse.like;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.repository.internal.UserRepositoryImpl;
import at.fhj.swd14.pse.user.User;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class LikeServiceHelperTest {

	@Mock
	private UserRepositoryImpl userRepository;
	
	private User user1;
	private User user2;
	private User user3;
	private List<User> users = new ArrayList<User>();
	private LikeServiceHelper likeServiceHelper;
	
	@Before
	public void setup() {
		user1 = new User(1L);
		user2 = new User(2L);
		users.add(user1);
		users.add(user2);
		user3 = new User(3L);
		likeServiceHelper = new LikeServiceHelper(users,userRepository);
	}
	
	@Test
	public void likeServiceHelperFirstTest() {
		users = likeServiceHelper.processUser(1L);
		Assert.assertNotSame(users.get(0).getId(),1L);
	}
	
	@Test
	public void likeServiceHelperSecondTest() {
		Mockito.when(userRepository.find(3L)).thenReturn(user3);
		users = likeServiceHelper.processUser(3L);
		Assert.assertEquals(users.get(2).getId(),user3.getId());
	}
}
