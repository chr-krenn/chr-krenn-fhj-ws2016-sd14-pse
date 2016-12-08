package at.fhj.swd14.pse.person.tools;

import java.io.Serializable;

import at.fhj.swd14.pse.person.HobbyDto;
import at.fhj.swd14.pse.person.KnowledgeDto;
import at.fhj.swd14.pse.person.MailaddressDto;
import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PhonenumberDto;

public class PersonVerifier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PersonBean bean;
	
	public PersonVerifier(PersonBean bean)
	{
		if(bean==null)
			throw new IllegalArgumentException("bean may not be null");
		this.bean = bean;
	}

	public boolean verifyName()
	{
		if(bean.getPerson().getFirstname()==null||bean.getPerson().getFirstname().length()==0)
		{
			bean.growl("Incorrect Input","Firstname is empty");
			return false;
		}
		if(bean.getPerson().getLastname()==null||bean.getPerson().getLastname().length()==0)
		{
			bean.growl("Incorrect Input","Lastname is empty");
			return false;
		}
		return true;
	}
	
	public boolean verifyMail(MailaddressDto mail)
	{
		if(mail==null||mail.getValue()==null||mail.getValue().length()==0)
		{
			bean.growl("Incorrect Input","Cannot add empty mail address to person");
			return false;
		}
		if(!mail.getValue().matches("[a-z0-9]+@[a-z0-9\\.]+"))
		{
			bean.growl("Incorrect Input","Mailaddress "+mail.getValue()+" is not a valid mail address");
			return false;
		}
		return true;
	}
	
	public boolean verifyHobby(HobbyDto hobby)
	{
		if(hobby==null||hobby.getValue()==null||hobby.getValue().length()==0)
		{
			bean.growl("Incorrect Input","Cannot add empty hobby to person");
			return false;
		}
		return true;
	}
	
	public boolean verifyKnowledge(KnowledgeDto knowledge)
	{
		if(knowledge == null||knowledge.getValue()==null||knowledge.getValue().length()==0)
		{
			bean.growl("Incorrect Input","Cannot add empty knowledge to person");
			return false;
		}
		return true;
	}
	
	public boolean verifyNumber(PhonenumberDto number)
	{
		if(number==null||number.getValue()==null||number.getValue().length()==0)
		{
			bean.growl("Incorrect Input","Cannot add empty phonenumber to person");
			return false;
		}
		if(!number.getValue().matches("\\+?[0-9]+"))
		{
			bean.growl("Incorrect Input","The phonenumber "+number.getValue()+" is not a valid phonenumber");
			return false;
		}
		return true;
	}
	
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
