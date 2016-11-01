package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }
}
