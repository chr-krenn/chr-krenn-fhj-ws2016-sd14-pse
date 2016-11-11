package at.fhj.swd14.pse.person;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.converter.StatusConverter;
import at.fhj.swd14.pse.repository.PersonImageRepository;
import at.fhj.swd14.pse.repository.PersonRepository;
import at.fhj.swd14.pse.repository.PersonStatusRepository;
import at.fhj.swd14.pse.repository.UserRepository;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

/**
 * Implementation of the service for the frontend
 * @author Patrick Kainz
 * @author Patrick Papst
 */
@Stateless
public class PersonServiceImpl implements PersonService {

	@EJB
	private PersonRepository repository;
	
	@EJB
	private UserRepository userRepo;
	
	@EJB
	private PersonImageRepository imgRepo;
	
	@EJB
	private PersonStatusRepository statusRepo;
	
	@EJB
	private UserService userService;
	
	@EJB
	private PersonVerifier verifier;
	
	@Override
	public PersonDto find(long id) {
		return PersonConverter.convert(repository.find(id));
	}

	@Override
	public PersonDto findByUser(UserDto user) {
		return PersonConverter.convert(repository.findByUserId(user.getId()));
	}

	@Override
	public Collection<PersonDto> findAllUser() {
		return PersonConverter.convertToDtoList(repository.findAll());
	}

	
	
	@Override
	public void saveLoggedInPerson(PersonDto person) {
		//first of all check if the person is null
		if(person==null)
			throw new IllegalArgumentException("Cannot insert null as person");
		//we need to verify the Dto object before we can store it into the database
		//step 1, check if the given user exists
		verifier.verifyUser(person);
		//step 2, check status
		verifier.verifyStatus(person);
		//step 3, check if firstname and lastname are set
		verifier.verifyNotNull(person);
		//step 4, check department if it is provided
		verifier.verifyDepartment(person);
		//step 5, check and correlate additional data
		verifier.correlateHobbies(person);
		verifier.correlateKnowledges(person);
		verifier.correlateMails(person);
		verifier.correlateNumbers(person);
		//step 6 convert the person
		Person personEntity = PersonConverter.convert(person);
		//step 7 save
		repository.update(personEntity);
	}

	@Override
	public Collection<StatusDto> findAllStati() {
		return StatusConverter.convertToDtoList(statusRepo.findAll());
	}

	@Override
	public void savePersonImage(PersonDto person, byte[] imageData, String contentType) {
		PersonImage existing = imgRepo.getByPersonId(person.getId());
		if(existing!=null)
			imgRepo.remove(existing);
		PersonImage img = new PersonImage();
		img.setData(imageData);
		img.setContentType(contentType);
		
		Person personEntity = repository.find(person.getId());
		if(personEntity == null)
			throw new IllegalArgumentException("Person does not exists");
		
		img.setPerson(personEntity);
		
		imgRepo.save(img);
	}

}
