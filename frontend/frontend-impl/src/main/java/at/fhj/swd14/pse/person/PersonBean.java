package at.fhj.swd14.pse.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.department.DepartmentService;
import at.fhj.swd14.pse.person.tools.LoggedInPersonPageHandler;
import at.fhj.swd14.pse.person.tools.PersonComparator;
import at.fhj.swd14.pse.person.tools.PersonPageHandler;
import at.fhj.swd14.pse.person.tools.PersonVerifier;
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
	
	@EJB(name = "ejb/DepartmentService")
    private DepartmentService departmentService;
	
	private PersonDto person;
	private List<StatusDto> stati;
	private List<DepartmentDto> departments;
	private String newMail=null;
	private String newKnowledge=null;
	private String newHobby=null;
	private String newNumber=null;
	private PersonVerifier verifier;
	private PersonPageHandler personPageHandler;
	private LoggedInPersonPageHandler loggedInPersonPageHandler;
	private Long loggedInUserId; 
	
	public Long getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(Long loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public PersonVerifier getVerifier() {
		return verifier;
	}
	
	public PersonService getPersonService() {
		return personService;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	
	public String getNewKnowledge() {
		return newKnowledge;
	}

	public void setNewKnowledge(String newKnowledge) {
		this.newKnowledge = newKnowledge;
	}

	public String getNewHobby() {
		return newHobby;
	}

	public void setNewHobby(String newHobby) {
		this.newHobby = newHobby;
	}

	public String getNewNumber() {
		return newNumber;
	}

	public void setNewNumber(String newNumber) {
		this.newNumber = newNumber;
	}

	public String getNewMail() {
		return newMail;
	}

	public void setNewMail(String newMail) {
		this.newMail = newMail;
	}

	public List<DepartmentDto> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentDto> departments) {
		this.departments = departments;
	}

	public List<StatusDto> getStati() {
		return stati;
	}

	public void setStati(List<StatusDto> stati) {
		this.stati = stati;
	}

	
	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}

	
	
	@PostConstruct
	public void init()
	{
		try{
			//we will have to init with the logged in user if possible, because right after login the render method 
			//is not called for some reason
			verifier = new PersonVerifier(this);
			personPageHandler = new PersonPageHandler(this);
			loggedInPersonPageHandler = new LoggedInPersonPageHandler(this);
			showLoggedInPerson();
			LOGGER.debug("PersonBean initialized successfully");
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}

	public void growl(String summary, Exception ex)
	{
		growl(summary,ex.getMessage());
	}
	
	public void growl(String summary, String message)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(summary,  message) );
		LOGGER.trace("Send growl to user with summary "+summary+" and message "+message);
	}
	
	public String showPersonByUserId()
	{
		try{
			return personPageHandler.showPersonByUserId();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
			return null;
		}
	}
	
	
	
	public String showLoggedInPerson()
	{
		try{
			return loggedInPersonPageHandler.showLoggedInPerson();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
			return null;
		}
	}

	public String createLoggedInPerson()
	{
		try{
			return loggedInPersonPageHandler.createLoggedInPerson();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
			return null;
		}
	}
	
	
	public void saveData()
	{
		try{
			loggedInPersonPageHandler.saveData();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public void clearImgUrl()
	{
		try{
			loggedInPersonPageHandler.clearImgUrl();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public String savePerson()
	{
		try{
			return loggedInPersonPageHandler.savePerson();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
			return null;
		}
	}
	
	public void addMail()
	{
		try{
			loggedInPersonPageHandler.addMail();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public void addKnowledge()
	{
		try{
			loggedInPersonPageHandler.addKnowledge();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public void addHobby()
	{
		try{
			loggedInPersonPageHandler.addHobby();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public void addNumber()
	{
		try{
			loggedInPersonPageHandler.addNumber();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}


	public void removeMail()
	{
		try{
			loggedInPersonPageHandler.removeMail();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public void removeKnowledge()
	{
		try{
			loggedInPersonPageHandler.removeKnowledge();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public void removeHobby()
	{
		try{
			loggedInPersonPageHandler.removeHobby();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public void removeNumber()
	{
		try{
			loggedInPersonPageHandler.removeNumber();
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
			loggedInPersonPageHandler.handleFileUpload(event);
		}
		catch(Exception ex)
		{
			growl("Error occured",ex);
		}
    }

	public void changeFriendState(long personID){
		Long loggedInUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
		personService.changeFriendState(loggedInUserId, personID);
	}
	
	public Collection<PersonDto> showAllPersons(){
		Long loggedInUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
		List<PersonDto> allPersons = new ArrayList<PersonDto>(personService.findAllUser(loggedInUserId));
		Collections.sort(allPersons,new PersonComparator());
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
