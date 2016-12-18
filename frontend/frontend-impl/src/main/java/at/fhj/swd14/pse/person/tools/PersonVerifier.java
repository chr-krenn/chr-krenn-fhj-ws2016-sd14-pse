package at.fhj.swd14.pse.person.tools;

import java.io.Serializable;

import at.fhj.swd14.pse.person.HobbyDto;
import at.fhj.swd14.pse.person.KnowledgeDto;
import at.fhj.swd14.pse.person.MailaddressDto;
import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PhonenumberDto;

/**
 * This class verifies the person of the bean
 * @author Patrick Kainz
 *
 */
public class PersonVerifier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String ERR_INVALID_INPUT = "Incorrect Input";
	private PersonBean bean;
	
	public PersonVerifier(PersonBean bean)
	{
		if(bean==null)
			throw new IllegalArgumentException("bean may not be null");
		this.bean = bean;
	}

	/**
	 * Verify the first and lastname, which have to be neither null nor empty
	 * @return true on success, false on failure
	 */
	public boolean verifyName()
	{
		if(bean.getPerson().getFirstname()==null||bean.getPerson().getFirstname().length()==0)
		{
			bean.growl(ERR_INVALID_INPUT,"Firstname is empty");
			return false;
		}
		if(bean.getPerson().getLastname()==null||bean.getPerson().getLastname().length()==0)
		{
			bean.growl(ERR_INVALID_INPUT,"Lastname is empty");
			return false;
		}
		return true;
	}
	
	/**
	 * Verifies the mail address which has to bei neither null nor empty and has to have the correct format
	 * @param mail MailaddressDto to verify
	 * @return true on success, false on failure
	 */
	public boolean verifyMail(MailaddressDto mail)
	{
		if(mail==null||mail.getValue()==null||mail.getValue().length()==0)
		{
			bean.growl(ERR_INVALID_INPUT,"Cannot add empty mail address to person");
			return false;
		}
		if(!mail.getValue().matches("[a-z0-9]+@[a-z0-9\\.]+"))
		{
			bean.growl(ERR_INVALID_INPUT,"Mailaddress "+mail.getValue()+" is not a valid mail address");
			return false;
		}
		return true;
	}
	
	/**
	 * Verifies the hobby, which has to be neither null nor empty
	 * @param hobby HobbyDto to verify
	 * @return true on success, false on failure
	 */
	public boolean verifyHobby(HobbyDto hobby)
	{
		if(hobby==null||hobby.getValue()==null||hobby.getValue().length()==0)
		{
			bean.growl(ERR_INVALID_INPUT,"Cannot add empty hobby to person");
			return false;
		}
		return true;
	}
	
	/**
	 * Verifies the KnowledgeDto which has to be neither null nor empty
	 * @param knowledge KnowledgeDto to verify
	 * @return true on success, false  on failure
	 */
	public boolean verifyKnowledge(KnowledgeDto knowledge)
	{
		if(knowledge == null||knowledge.getValue()==null||knowledge.getValue().length()==0)
		{
			bean.growl(ERR_INVALID_INPUT,"Cannot add empty knowledge to person");
			return false;
		}
		return true;
	}
	
	/**
	 * Verifies the phonenumber, which has to be neither null nor empty and has to have the correct format
	 * @param number number to verify
	 * @return true on success, false on failure
	 */
	public boolean verifyNumber(PhonenumberDto number)
	{
		if(number==null||number.getValue()==null||number.getValue().length()==0)
		{
			bean.growl(ERR_INVALID_INPUT,"Cannot add empty phonenumber to person");
			return false;
		}
		if(!number.getValue().matches("\\+?[0-9]+"))
		{
			bean.growl(ERR_INVALID_INPUT,"The phonenumber "+number.getValue()+" is not a valid phonenumber");
			return false;
		}
		return true;
	}
	
	/**
	 * Verifies all attributes of the person in the bean
	 * @return true on success, false on failure
	 */
	public boolean verifyPerson()
	{ 
		if(!verifyName())
			return false;
		for(MailaddressDto mail : bean.getPerson().getAdditionalMails())
		{
			if(!verifyMail(mail))
				return false;
		}
		for(HobbyDto hobby : bean.getPerson().getHobbies())
		{
			if(!verifyHobby(hobby))
				return false;
		}
		for(KnowledgeDto knowledge : bean.getPerson().getKnowledges())
		{
			if(!verifyKnowledge(knowledge))
				return false;
		}
		for(PhonenumberDto number : bean.getPerson().getPhonenumbers())
		{
			if(!verifyNumber(number))
				return false;
		}
		return true;
	}
	
}
