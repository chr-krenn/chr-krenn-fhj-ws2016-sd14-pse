package at.fhj.swd14.pse.user;

import javax.ejb.Remote;

@Remote
public interface UserService {

    long save(UserDto user);

    UserDto find(long id);

}
