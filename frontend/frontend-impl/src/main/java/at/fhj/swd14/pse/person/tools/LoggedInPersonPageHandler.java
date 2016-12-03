package at.fhj.swd14.pse.person.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import at.fhj.swd14.pse.department.DepartmentConverter;
import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.department.DepartmentService;
import at.fhj.swd14.pse.person.HobbyDto;
import at.fhj.swd14.pse.person.KnowledgeDto;
import at.fhj.swd14.pse.person.MailaddressDto;
import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.person.PhonenumberDto;
import at.fhj.swd14.pse.person.StatusDto;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

public class LoggedInPersonPageHandler {

	private static final Logger LOGGER = LogManager.getLogger(LoggedInPersonPageHandler.class);
	
	private PersonBean bean;
	private UserService userService;
	private PersonService personService;
	private DepartmentService departmentService;
	private PersonVerifier verifier;
	
	public LoggedInPersonPageHandler(PersonBean bean)
	{
		if(bean==null)
			throw new IllegalArgumentException("bean may not be null");
		this.bean=bean;
		userService = bean.getUserService();
		personService = bean.getPersonService();
		departmentService=bean.getDepartmentService();
		verifier = bean.getVerifier();
	}
	
	private void loadStati()
	{
		bean.setStati(new ArrayList<StatusDto>(personService.findAllStati()));
	}
	
	private void loadDepartments()
	{
		bean.setDepartments(new ArrayList<DepartmentDto>(departmentService.findAll()));
		DepartmentConverter.setDepartments(bean.getDepartments());
	}
	
	public String showLoggedInPerson()
	{
		Long loggedInUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
		if(bean.getPerson()==null||bean.getPerson().getId()==null||bean.getPerson().getUser()==null||
				bean.getPerson().getUser().getId()==null||bean.getPerson().getUser().getId()!=loggedInUserId)
		{
			UserDto loggedInUser = userService.find(loggedInUserId);
			PersonDto person = personService.findByUser(loggedInUser);
			if(person==null)
				person = new PersonDto();
			bean.setPerson(person);
		}
		loadStati();
		loadDepartments();
		return "/user";
	}

	public String createLoggedInPerson()
	{
		if(verifier.verifyName())
		{
			Long loggedInUserId =  ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
			UserDto loggedInUser = userService.find(loggedInUserId);
			bean.getPerson().setUser(loggedInUser);
			bean.getPerson().setStatus(new StatusDto("online"));
			personService.saveLoggedInPerson(bean.getPerson());
		}
		return showLoggedInPerson();
	}
	
	public void saveData()
	{
		//we actually need to do nothing here, as long as the value is synchronized to the server
	}
	
	public void clearImgUrl()
	{
		bean.getPerson().setImageUrl(null);
		saveData();
	}
	
	public String savePerson()
	{
		if(verifier.verifyPerson())
		{
			personService.saveLoggedInPerson(bean.getPerson());
		}
		return "/user";
	}
	
	public void addMail()
	{
		for(MailaddressDto existing : bean.getPerson().getAdditionalMails())
		{
			if(existing.getValue().equals(bean.getNewMail()))
			{
				bean.setNewMail(null);
				bean.growl("Incorrect Input","Mail "+bean.getNewMail()+" already exists");
				return;
			}
		}
		MailaddressDto mail = new MailaddressDto();
		mail.setValue(bean.getNewMail());
		mail.setPerson(bean.getPerson());
		if(verifier.verifyMail(mail))
		{
			bean.getPerson().getAdditionalMails().add(mail);
			bean.setNewMail(null);
		}
	}
	
	public void addKnowledge()
	{
		for(KnowledgeDto existing : bean.getPerson().getKnowledges())
		{
			if(existing.getValue().equals(bean.getNewKnowledge()))
			{
				bean.setNewNumber(null);
				bean.growl("Incorrect Input","Knowledge "+bean.getNewKnowledge()+" already exists");
				return;
			}
		}
		KnowledgeDto knowledge = new KnowledgeDto();
		knowledge.setValue(bean.getNewKnowledge());
		knowledge.setPerson(bean.getPerson());
		if(verifier.verifyKnowledge(knowledge))
		{
			bean.getPerson().getKnowledges().add(knowledge);
			bean.setNewNumber(null);
		}
	}
	
	public void addHobby()
	{
		for(HobbyDto existing : bean.getPerson().getHobbies())
		{
			if(existing.getValue().equals(bean.getNewHobby()))
			{
				bean.setNewHobby(null);
				throw new IllegalArgumentException("Hobby already exists");
			}
		}
		HobbyDto hobby = new HobbyDto();
		hobby.setValue(bean.getNewHobby());
		hobby.setPerson(bean.getPerson());
		if(verifier.verifyHobby(hobby))
		{
			bean.getPerson().getHobbies().add(hobby);
			bean.setNewHobby(null);
		}
	}
	
	public void addNumber()
	{
		for(PhonenumberDto existing : bean.getPerson().getPhonenumbers())
		{
			if(existing.getValue().equals(bean.getNewNumber()))
			{
				bean.setNewNumber(null);
				bean.growl("Incorrect Input","Number "+bean.getNewNumber()+" already exists");
				return;
			}
		}
		PhonenumberDto number = new PhonenumberDto();
		number.setValue(bean.getNewNumber());
		number.setPerson(bean.getPerson());
		if(verifier.verifyNumber(number))
		{
			bean.getPerson().getPhonenumbers().add(number);
			bean.setNewNumber(null);
		}
	}
	
	public void removeMail()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String value = params.get("value");
	    for(MailaddressDto address : bean.getPerson().getAdditionalMails())
	    {
	    	if(value.equals(address.getValue()))
	    		bean.getPerson().getAdditionalMails().remove(address);
	    }
	}
	
	public void removeKnowledge()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String value = params.get("value");
	    for(KnowledgeDto knowledge : bean.getPerson().getKnowledges())
	    {
	    	if(value.equals(knowledge.getValue()))
	    		bean.getPerson().getKnowledges().remove(knowledge);
	    }
	}
	
	public void removeHobby()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String value = params.get("value");
	    for(HobbyDto hobby : bean.getPerson().getHobbies())
	    {
	    	if(value.equals(hobby.getValue()))
	    		bean.getPerson().getHobbies().remove(hobby);
	    }
	}
	
	public void removeNumber()
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String value = params.get("value");
	    for(PhonenumberDto number : bean.getPerson().getPhonenumbers())
	    {
	    	if(value.equals(number.getValue()))
	    		bean.getPerson().getPhonenumbers().remove(number);
	    }
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		showLoggedInPerson();
		personService.savePersonImage(bean.getPerson(), event.getFile().getContents(), event.getFile().getContentType());
		bean.getPerson().setImageUrl("/swd14-fe/personImage?id="+bean.getPerson().getId());
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try{
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
		catch(IOException ex)
		{
			bean.growl("Internal Error","Error during page refresh");
			LOGGER.error("Error during page refresh",ex);
		}
    }
	
}
