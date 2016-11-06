package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.user.User;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

@Local
@Singleton
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }
}
