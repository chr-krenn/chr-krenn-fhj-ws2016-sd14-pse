package at.fhj.swd14.pse.person.tools;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

/**
 * Handles all interaction with the PersonPage for PersonBean
 * @author Patrick Kainz
 *
 */
public class PersonPageHandler implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(PersonPageHandler.class);
	
	private PersonBean bean;
	private UserService userService;
	private PersonService personService;
	
	public PersonPageHandler(PersonBean bean)
	{
		if(bean==null)
			throw new IllegalArgumentException("bean may not be null");
		//we do this handling for the bean so use it as sole datasource
		this.bean=bean;
		userService = bean.getUserService();
		personService = bean.getPersonService();
	}
	
	/**
	 * Sets the person of the user (Parameter "userId" GET/POST) to the bean
	 * @return next page to navigate to
	 */
	public String showPersonByUserId()
	{
		//get the loggedinuser, as the frontend displays differently if we are showing our own user
		Long loggedInUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
		bean.setLoggedInUserId(loggedInUserId);
		//get the user id as POST/GET parameter
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    int userId = Integer.parseInt(params.get("userId"));
		//find the user
	    UserDto user = userService.find(userId);
		
		PersonDto person;
		

		if(user==null)
		{
			person=null;
			LOGGER.debug("Found no user for id: "+userId);
			bean.growl("Error","User with id "+userId+" not found");
		}
		else
		{
			LOGGER.debug("Found user: "+user.toString()+ " for id: "+userId);
			//find the person
			person=personService.findByUser(user);
		}
		if(person!=null)
		{
			LOGGER.debug("Foud person: "+person.toString());
		}
		else
		{
			LOGGER.debug("Found no person for id: "+userId);
			bean.growl("Error","No Person assigned to User with id "+userId+"");
		}
		//and set the person to the frontend (or null)
		bean.setPerson(person);
		
		return "/myprofile";
	}
}
