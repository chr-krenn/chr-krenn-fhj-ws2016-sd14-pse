package at.fhj.swd14.pse.person;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.repository.AbstractPersonInformationRepository;
import at.fhj.swd14.pse.repository.DepartmentRepository;
import at.fhj.swd14.pse.repository.HobbyRepository;
import at.fhj.swd14.pse.repository.KnowledgeRepository;
import at.fhj.swd14.pse.repository.MailaddressRepository;
import at.fhj.swd14.pse.repository.PersonRepository;
import at.fhj.swd14.pse.repository.PersonStatusRepository;
import at.fhj.swd14.pse.repository.PhonenumberRepository;
import at.fhj.swd14.pse.repository.UserRepository;
import at.fhj.swd14.pse.user.User;

/**
 * Verification class for persondtos
 * @author Patrick Kainz
 *
 */
@Local
@Singleton
public class PersonVerifier {

	@EJB
	private PersonRepository repository;
	@EJB
	private UserRepository userRepo;
	@EJB
	private PersonStatusRepository statusRepo;
	@EJB
	private DepartmentRepository departmentRepo;
	@EJB
	private PhonenumberRepository phonenumberRepo;
	@EJB
	private MailaddressRepository mailaddressRepo;
	@EJB
	private HobbyRepository hobbyRepo;
	@EJB
	private KnowledgeRepository knowledgeRepo;
	
	public void verifyUser(PersonDto person)
	{
		if(person.getUser()==null||person.getUser().getId()==null)
			throw new VerificationException("No user provided");
		User user = userRepo.find(person.getUser().getId());
		if(user==null)
			throw new VerificationException("Given user not found in the database");
	}
	
	public void verifyStatus(PersonDto person)
	{
		if(person.getStatus()==null||person.getStatus().getName()==null)
		{
			throw new VerificationException("No status provided");
		}
		Status status = statusRepo.findByName(person.getStatus().getName());
		if(status==null)
			throw new VerificationException("Status invalid");
	}
	
	public void verifyNotNull(PersonDto person)
	{
		if(person.getFirstname()==null||person.getFirstname().length()==0
				||person.getLastname()==null||person.getLastname().length()==0)
			throw new VerificationException("No first and lastname given");
	}
	
	public void verifyDepartment(PersonDto person)
	{
		//department may be null
		if(person.getDepartment()!=null&&person.getDepartment().getId()!=null)
		{
			Department department = departmentRepo.find(person.getDepartment().getId());
			if(department==null)
				throw new VerificationException("Department not found in database");
		}
	}
	
	private <T extends AbstractPersonInformation,K extends AbstractPersonInformationDto> 
		void correlatePersonInformation(PersonDto person, List<K> infos, 
			AbstractPersonInformationRepository<T> repo)
	{
		for(AbstractPersonInformationDto info : infos)
		{
			//check if the id is set, if so we just need to check if
			if(info.getId()!=null)
			{
				AbstractPersonInformation real = (AbstractPersonInformation)repo.find(info.getId());
				if(real==null)
					throw new VerificationException("The PersonInformation object with the id "+info.getId()+" does not exist");
			}
			else
			{
				if(person.getId()!=null)//if we are adding a new person, there is no need to check this
				{
					//we need to check if that entry already exists
					AbstractPersonInformation real = (AbstractPersonInformation)repo.findByValue(person.getId(), info.getValue());
					if(real!=null)
					{
						info.setId(real.getId()); //if it already exists just use the existing id
					}
				}
			}
		}
	}
	
	public void correlateNumbers(PersonDto person) {
		this.<Phonenumber,PhonenumberDto>correlatePersonInformation
		(person, person.getPhonenumbers(), phonenumberRepo);
	}

	public void correlateMails(PersonDto person) {
		this.<Mailaddress,MailaddressDto>correlatePersonInformation
		(person, person.getAdditionalMails(), mailaddressRepo);
	}

	public void correlateKnowledges(PersonDto person) {
		this.<Knowledge,KnowledgeDto>correlatePersonInformation
		(person, person.getKnowledges(), knowledgeRepo);
	}

	public void correlateHobbies(PersonDto person) {
		this.<Hobby,HobbyDto>correlatePersonInformation
		(person, person.getHobbies(), hobbyRepo);
	}

	public PersonVerifier()
	{
		
	}
	
}
