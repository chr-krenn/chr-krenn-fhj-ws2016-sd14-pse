package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.user.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }
}
