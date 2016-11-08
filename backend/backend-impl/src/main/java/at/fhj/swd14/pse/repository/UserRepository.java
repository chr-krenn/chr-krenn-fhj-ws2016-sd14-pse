package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.user.User;

@Local
@Singleton
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }
}
