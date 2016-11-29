package at.fhj.swd14.pse.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import at.fhj.swd14.pse.contact.Contact;
import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.converter.PersonImageConverter;
import at.fhj.swd14.pse.converter.StatusConverter;
import at.fhj.swd14.pse.repository.*;
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
	private ContactRepository contactRepository;
	
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
	public Collection<PersonDto> findAllUser(long loggedInUserID) {
		Collection<PersonDto> resultList=PersonConverter.convertToDtoList(repository.findAll());

		Person loggedInPerson=repository.findByUserId(loggedInUserID);
		Collection<Contact> contacts=new ArrayList<Contact>();
		if(loggedInPerson!=null){
			contacts=contactRepository.findByPersonId(loggedInPerson.getId());
		}
		
		for(PersonDto p : new ArrayList<PersonDto>(resultList)){
			if(PersonConverter.convert(p).getId() == loggedInPerson.getId()){
				resultList.remove(p);
			}
		}
		resultList.forEach(p->p.setFriendState("Freund hinzufügen"));
		//change friend state for already added friends
		for(Contact contact : contacts){
			long otherPersonID=contact.getContactPK().getPerson1Id();
			if(otherPersonID==loggedInPerson.getId()){
				otherPersonID=contact.getContactPK().getPerson2Id();
			}
			final long fOtherPersonID=otherPersonID;
			resultList.stream().filter(p->p.getId()==fOtherPersonID).findFirst().get().setFriendState("Entfernen");
		}

		return resultList;
	}


	/**
	 * Toggles the friend state of the loggedInUser with the other person
	 * */
	@Override
	public void changeFriendState(long loggedInUserID, long otherPersonID) {
		Collection<PersonDto> resultList=PersonConverter.convertToDtoList(repository.findAll());

		Person loggedInPerson=repository.findByUserId(loggedInUserID);

		Collection<Contact> existingContacts=contactRepository.findByPersonId(loggedInPerson.getId());
		Optional<Contact> contactOptional=existingContacts.stream().filter(c->c.getContactPK().getPerson1Id()==otherPersonID || c.getContactPK().getPerson2Id()==otherPersonID).findFirst();
		if(contactOptional.isPresent()){
			//contact exists -> remove
			contactRepository.remove(contactOptional.get());
		}else{
			//add new contact
			contactRepository.save(new Contact(loggedInPerson.getId(), otherPersonID));
		}
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
		{
			imgRepo.remove(existing);
			imgRepo.flush();
		}
		PersonImage img = new PersonImage();
		img.setData(imageData);
		img.setContentType(contentType);
		
		Person personEntity = repository.find(person.getId());
		if(personEntity == null)
			throw new IllegalArgumentException("Person does not exists");
		
		img.setPerson(personEntity);
		
		imgRepo.save(img);
	}

	@Override
	public PersonImageDto getPersonImage(Long personid) {
		PersonImage img = imgRepo.getByPersonId(personid);
		return PersonImageConverter.convert(img);
	}

}
