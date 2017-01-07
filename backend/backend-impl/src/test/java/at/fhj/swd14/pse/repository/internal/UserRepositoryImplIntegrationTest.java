package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserAssert;
import at.fhj.swd14.pse.user.UserConverter;
import org.junit.Ignore;

@Ignore
public class UserRepositoryImplIntegrationTest extends AbstractRepositoryIDIntegrationTest<User> {

    public UserRepositoryImplIntegrationTest() {
        super(User.class);
    }

    @Override
    protected long getDummyId(User dummy) {
        return dummy.getId();
    }

    @Override
    protected AbstractRepository<User> getRepository() {
        return new UserRepositoryImpl();
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
        UserAssert.assertEquals(UserConverter.convert(expected), UserConverter.convert(actual));
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
