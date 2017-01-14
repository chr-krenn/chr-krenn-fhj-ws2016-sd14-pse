package at.fhj.swd14.pse.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @EJB
    private UserRepository userRepository;

    @Override
    public long save(UserDto user) {
        LOGGER.trace("Saving user");
        try {
            User userDo = UserConverter.convert(user);
            userRepository.save(userDo);
            LOGGER.info("Saved user {}", userDo.getId());
            return userDo.getId();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new UserServiceException("Could not save user");
        }
    }

    @Override
    public UserDto find(long id) {
        LOGGER.trace("Finding user with id {}", id);
        try {
            return UserConverter.convert(userRepository.find(id));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new UserServiceException("Could not find user");
        }
    }
}