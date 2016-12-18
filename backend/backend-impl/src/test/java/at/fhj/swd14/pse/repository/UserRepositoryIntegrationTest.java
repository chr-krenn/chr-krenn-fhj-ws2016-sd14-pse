package at.fhj.swd14.pse.repository;

import org.junit.Ignore;

import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDtoTester;

@Ignore
public class UserRepositoryIntegrationTest extends AbstractRepositoryIDIntegrationTest<User> {

	public UserRepositoryIntegrationTest() {
		super(User.class);
	}
	
	@Override
	protected long getDummyId(User dummy) {
		return ((User)dummy).getId();
	}

	@Override
	protected AbstractRepository<User> getRepository() {
		return new UserRepository();
	}

	@Override
	protected User createDummyEntity() {
		User user = new User();
		user.setMail("test@test.de");
		user.setPassword("12345");
		user.setSalt("12345");
		return user;
	}

	@Override
	protected void assertEquals(User expected, User actual) {
		UserDtoTester.assertEquals(UserConverter.convert(expected), UserConverter.convert(actual));
	}

	@Override
	protected User modifyDummy(User dummy) {
		dummy.setPassword("hallo");
		return dummy;
	}

	@Override
	protected void copyDummyPK(User destination, User source) {
		destination.setId(source.getId());
	}

}
