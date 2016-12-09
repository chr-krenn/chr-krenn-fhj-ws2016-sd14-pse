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


/**
 * Bean for all person pages
 * @author Patrick Kainz
 *
 */
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

	
	
	protected void setPersonPageHandler(PersonPageHandler personPageHandler) {
		this.personPageHandler = personPageHandler;
	}

	protected void setLoggedInPersonPageHandler(LoggedInPersonPageHandler loggedInPersonPageHandler) {
		this.loggedInPersonPageHandler = loggedInPersonPageHandler;
	}

	/**
	 * Initialize Person data, as we need to display it immediately
	 */
	@PostConstruct
	public void init()
	{
		//we will have to init with the logged in user if possible, because right after login the render method 
		//is not called for some reason
		//we defer many things to other classes so create them now
		verifier = new PersonVerifier(this);
		personPageHandler = new PersonPageHandler(this);
		loggedInPersonPageHandler = new LoggedInPersonPageHandler(this);
		//load the logged in person already
		showLoggedInPerson();
		LOGGER.debug("PersonBean initialized successfully");
	}

	/**
	 * Print an exception on the page
	 * @param summary summary message
	 * @param ex Exception to print
	 */
	public void growl(String summary, Exception ex)
	{
		growl(summary,ex.getMessage());
	}
	
	/**
	 * Print a message to the page
	 * @param summary summary message
	 * @param message message to print
	 */
	public void growl(String summary, String message)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(summary,  message) );
		LOGGER.trace("Send growl to user with summary "+summary+" and message "+message);
	}
	
	/**
	 * Shows the person by the userid passed
	 * @return next page to navigate to
	 */
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
	
	
	/**
	 * Shows the logged in person
	 * @return next page to navigate to
	 */
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

	/**
	 * Creates a new person for the logged in user
	 * @return next page to navigate to
	 */
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
	
	/**
	 * Just here for sync with the client
	 */
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
	
	/**
	 * Resets the persons image url to null
	 */
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
	
	/**
	 * Saves the person to the backend
	 * @return next page to navigate to
	 */
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
	
	/**
	 * Adds a mail to the current person, only in frontend
	 */
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
	
	/**
	 * Adds a knowledge to the current person, only in frontend
	 */
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
	
	/**
	 * Adds a hobby to the current person, only in frontend
	 */
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
	
	/**
	 * Adds a number to the current person, only in frontend
	 */
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
	
	/**
	 * Removes the passed mail from the current person, only in frontend
	 */
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
	
	/**
	 * Removes the passed knowledge from the current person, only in frontend
	 */
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
	
	/**
	 * Removes the passed hobby from the current person, only in frontend
	 */
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
	
	/**
	 * Removes the passed number from the current person, only in frontend
	 */
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
	
	/**
	 * Handles a file upload from the page
	 * @param event event holding data and datatype
	 */
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
