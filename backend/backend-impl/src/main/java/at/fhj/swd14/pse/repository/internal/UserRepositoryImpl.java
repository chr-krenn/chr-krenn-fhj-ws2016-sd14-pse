package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

@Local
@Singleton
public class UserRepositoryImpl
        extends AbstractRepository<User>
        implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }
}
