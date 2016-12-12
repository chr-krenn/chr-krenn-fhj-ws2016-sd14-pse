package at.fhj.swd14.pse.user;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.repository.UserRepository;

@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    private UserRepository userRepository;

    @Override
    public long save(UserDto user) {
        User userDo = UserConverter.convert(user);
        userRepository.save(userDo);
        return userDo.getId();
    }

    @Override
    public UserDto find(long id) {
        return UserConverter.convert(userRepository.find(id));
    }
}
