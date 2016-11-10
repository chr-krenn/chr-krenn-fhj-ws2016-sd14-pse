package at.fhj.swd14.pse.person;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;


@Named
@SessionScoped
public class PersonBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(PersonBean.class);
	
	@EJB(name = "ejb/PersonService")
    private PersonService personService;
	
	@EJB(name = "ejb/UserService")
    private UserService userService;
	
	
	private PersonDto person;
	
	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}

	public String showPersonByUserId()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    int userId = Integer.parseInt(params.get("userId"));
		UserDto user = userService.find(userId);
		
		

		if(user==null)
		{
			person=null;
			LOGGER.debug("Found no user for id: "+userId);
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
		}
		
		return "/protected/personTest";
	}
	
	public String showLoggedInPerson()
	{
		person = personService.getLoggedInPerson();
		if(person==null)
			person = new PersonDto();
		return "/protected/loggedInPersonTest";
	}
	
	public String createLoggedInPerson()
	{
		Long loggedInUserId = 1L;//TODO: FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getId();
		UserDto loggedInUser = userService.find(loggedInUserId);
		person.setUser(loggedInUser);
		person.setStatus(new StatusDto("online"));
		personService.saveLoggedInPerson(person);
		return showLoggedInPerson();
	}
	
	public Collection<PersonDto> showAllPersons(){
		Collection<PersonDto> allPersons = personService.findAllUser();
		if(allPersons.size()==0){
			LOGGER.debug("Found no user in db");
		}
		else{
			LOGGER.debug("Found " + allPersons.size() +" person(s) in db");
			for(PersonDto p : allPersons){
				LOGGER.debug("Person: "+p.getFirstname() +" "+p.getLastname()+"\n");
			}
		}
		return allPersons;	
	}
	
}
