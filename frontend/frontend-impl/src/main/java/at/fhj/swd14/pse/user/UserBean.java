package at.fhj.swd14.pse.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Named;

@Named
public class UserBean {

    private static final Logger LOGGER = LogManager.getLogger(UserBean.class);

    @EJB(name = "ejb/UserService")
    private UserService userService;

    private UserDto user;

    public void save() {
        LOGGER.info("Creating user...");
        final long generatedId = userService.save(new UserDto());
        LOGGER.info("User with id {} created.", generatedId);

        user = userService.find(generatedId);
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
