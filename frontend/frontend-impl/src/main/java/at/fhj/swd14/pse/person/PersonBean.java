package at.fhj.swd14.pse.person;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import at.fhj.swd14.pse.department.DepartmentConverter;
import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.department.DepartmentService;
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
	
	@EJB(name = "ejb/DepartmentService")
    private DepartmentService departmentService;
	
	
	private PersonDto person;
	private List<StatusDto> stati;
	private List<DepartmentDto> departments;
	private String newMail=null;
	private String newKnowledge=null;
	private String newHobby=null;
	private String newNumber=null;
	
	
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
		//we will have to init with the logged in user if possible, because right after login the render method 
		//is not called for some reason
		showLoggedInPerson();
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
	
	private void loadStati()
	{
		stati = new ArrayList<StatusDto>(personService.findAllStati());
	}
	
	private void loadDepartments()
	{
		departments=new ArrayList<DepartmentDto>(departmentService.findAll());
		DepartmentConverter.setDepartments(departments);
	}
	
	public String showLoggedInPerson()
	{
		Long loggedInUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
		if(person==null||person.getUser()==null||person.getUser().getId()==null||person.getUser().getId()!=loggedInUserId)
		{
			UserDto loggedInUser = userService.find(loggedInUserId);
			person = personService.findByUser(loggedInUser);
			if(person==null)
				person = new PersonDto();
		}
		loadStati();
		loadDepartments();
		return "/protected/loggedInPersonTest";
	}
	
	public String createLoggedInPerson()
	{
		Long loggedInUserId =  ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
		UserDto loggedInUser = userService.find(loggedInUserId);
		person.setUser(loggedInUser);
		person.setStatus(new StatusDto("online"));
		personService.saveLoggedInPerson(person);
		return showLoggedInPerson();
	}
	
	public Collection<PersonDto> showAllPersons(){
		List<PersonDto> allPersons = new ArrayList<PersonDto>(personService.findAllUser()); 
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
	
	public void saveData()
	{
		//we actually need to do nothing here, as long as the value is synchronized to the server
	}
	
	public void clearImgUrl()
	{
		person.setImageUrl(null);
		saveData();
	}
	
	public String savePerson()
	{
		personService.saveLoggedInPerson(person);
		return showLoggedInPerson();
	}
	
	public void addMail()
	{
		for(MailaddressDto existing : person.getAdditionalMails())
		{
			if(existing.getValue().equals(newMail))
			{
				newMail = null;
				throw new IllegalArgumentException("Mail already exists");
			}
		}
		MailaddressDto mail = new MailaddressDto();
		mail.setValue(newMail);
		mail.setPerson(person);
		person.getAdditionalMails().add(mail);
		newMail=null;
	}
	
	public void addKnowledge()
	{
		for(KnowledgeDto existing : person.getKnowledges())
		{
			if(existing.getValue().equals(newKnowledge))
			{
				newKnowledge = null;
				throw new IllegalArgumentException("Knowledge already exists");
			}
		}
		KnowledgeDto knowledge = new KnowledgeDto();
		knowledge.setValue(newKnowledge);
		knowledge.setPerson(person);
		person.getKnowledges().add(knowledge);
		newKnowledge=null;
	}
	
	public void addHobby()
	{
		for(HobbyDto existing : person.getHobbies())
		{
			if(existing.getValue().equals(newHobby))
			{
				newHobby = null;
				throw new IllegalArgumentException("Hobby already exists");
			}
		}
		HobbyDto hobby = new HobbyDto();
		hobby.setValue(newHobby);
		hobby.setPerson(person);
		person.getHobbies().add(hobby);
		newHobby=null;
	}
	
	public void addNumber()
	{
		for(PhonenumberDto existing : person.getPhonenumbers())
		{
			if(existing.getValue().equals(newNumber))
			{
				newNumber = null;
				throw new IllegalArgumentException("Number already exists");
			}
		}
		PhonenumberDto number = new PhonenumberDto();
		number.setValue(newNumber);
		number.setPerson(person);
		person.getPhonenumbers().add(number);
		newNumber=null;
	}
	
	public void removeMail()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String value = params.get("value");
	    for(MailaddressDto address : person.getAdditionalMails())
	    {
	    	if(value.equals(address.getValue()))
	    		person.getAdditionalMails().remove(address);
	    }
	}
	
	public void removeKnowledge()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String value = params.get("value");
	    for(KnowledgeDto knowledge : person.getKnowledges())
	    {
	    	if(value.equals(knowledge.getValue()))
	    		person.getKnowledges().remove(knowledge);
	    }
	}
	
	public void removeHobby()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String value = params.get("value");
	    for(HobbyDto hobby : person.getHobbies())
	    {
	    	if(value.equals(hobby.getValue()))
	    		person.getHobbies().remove(hobby);
	    }
	}
	
	public void removeNumber()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String value = params.get("value");
	    for(PhonenumberDto number : person.getPhonenumbers())
	    {
	    	if(value.equals(number.getValue()))
	    		person.getPhonenumbers().remove(number);
	    }
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		personService.savePersonImage(person, event.getFile().getContents(), event.getFile().getContentType());
		person.setImageUrl("/swd14-fe/personImage?id="+person.getId());
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try{
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
		catch(IOException ex)
		{
			throw new IllegalArgumentException("Error during page refresh",ex);
		}
    }

}
