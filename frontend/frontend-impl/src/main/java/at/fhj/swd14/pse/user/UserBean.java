package at.fhj.swd14.pse.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(UserBean.class);

    @EJB(name = "ejb/UserService")
    private UserService userService;

    private UserDto user;
    
	/*
	 * Constructor
	 */
	public UserBean()
	{
		LOGGER.debug("Create: " + UserBean.class.getSimpleName());
		user = new UserDto();
	}

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
    
	/*
	 * Actions
	 */
	
	public String login()
	{
		LOGGER.debug("login()");
		if(true)
		{
			return "welcome";			
		}
		else
		{
			return "loginFailed";
		}
	}    
}
