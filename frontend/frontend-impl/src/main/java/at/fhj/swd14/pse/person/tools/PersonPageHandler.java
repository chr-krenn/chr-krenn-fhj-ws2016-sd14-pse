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
		this.bean=bean;
		userService = bean.getUserService();
		personService = bean.getPersonService();
	}
	
	public String showPersonByUserId()
	{
		Long loggedInUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
		bean.setLoggedInUserId(loggedInUserId);
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    int userId = Integer.parseInt(params.get("userId"));
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
		bean.setPerson(person);
		
		return "/myprofile";
	}
}
