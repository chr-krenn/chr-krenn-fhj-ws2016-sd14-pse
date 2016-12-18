package at.fhj.swd14.pse.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.contact.Contact;
import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.converter.PersonImageConverter;
import at.fhj.swd14.pse.converter.StatusConverter;
import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.repository.ContactRepository;
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
	
	private static final Logger LOGGER = LogManager.getLogger(PersonServiceImpl.class);

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
		try {
			LOGGER.trace("Finding person by id: "+id);
			return PersonConverter.convert(repository.find(id));
		} catch(Exception ex) {
			LOGGER.error("Exception during finding person by id "+id,ex);
			throw new PersonServiceException("Finding person by id failed for id "+id);
		}
	}

	@Override
	public PersonDto findByUser(UserDto user) {
		try {
			LOGGER.trace("Finding person by userid: " + user.getId());
			return PersonConverter.convert(repository.findByUserId(user.getId()));
		} catch(Exception ex) {
			LOGGER.error("Exception during finding person by userid "+user.getId(),ex);
			throw new PersonServiceException("Finding person by userid "+user.getId()+" failed");
		}
	}

	@Override
	public Collection<PersonDto> findAllUser(long loggedInUserID) {
		Collection<PersonDto> resultList=PersonConverter.convertToDtoList(repository.findAll());

		Person loggedInPerson=repository.findByUserId(loggedInUserID);
		Collection<Contact> contacts= new ArrayList<>();
		if(loggedInPerson!=null){
			contacts=contactRepository.findByPersonId(loggedInPerson.getId());
		}
		//remove logged in person from friend view
		for(PersonDto p : new ArrayList<>(resultList)){
			if(Objects.equals(PersonConverter.convert(p).getId(), loggedInPerson.getId())){
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
		try{
			//first of all check if the person is null
			if(person==null)
			{
				LOGGER.error("Cannot save null as person");
				throw new VerificationException("Cannot insert null as person");
			}
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
			
			LOGGER.trace("Person to save verified");
			//step 6 convert the person
			Person personEntity = PersonConverter.convert(person);
			LOGGER.trace("Person to save converted to entity");
			//step 7 save
			repository.update(personEntity);
			LOGGER.debug("Person stored");
		} catch(VerificationException ex) {
			LOGGER.debug("Invalid input was supplied by frontend: "+ex.getMessage(),ex);
			throw new PersonServiceException("Invalid input was supplied by frontend: "+ex.getMessage());
		} catch(Exception ex) {
			LOGGER.error("Exception during saving of person",ex);
			throw new PersonServiceException("Person could not be saved");
		}
	}

	@Override
	public Collection<StatusDto> findAllStati() {
		try{
			LOGGER.trace("Retrieving status values from database");
			return StatusConverter.convertToDtoList(statusRepo.findAll());
		} catch(Exception ex) {
			LOGGER.error("Error during finding all stati",ex);
			throw new PersonServiceException("Stati could not be found");
		}
	}

	@Override
	public void savePersonImage(PersonDto person, byte[] imageData, String contentType) {
		
		try{
			if(imageData==null||imageData.length==0)
			{
				LOGGER.error("Cannot save empty image");
				throw new VerificationException("Cannot save empty image");
			}
			
			if(person==null||person.getId()==null)
			{
				LOGGER.error("Cannot store image for empty person");
				throw new VerificationException("Cannot store image for empty person");
			}
			
			LOGGER.trace("Saving image for person "+person.getId());
			PersonImage existing = imgRepo.getByPersonId(person.getId());
			//delete any existing person images (only one per person allowed)
			if(existing!=null)
			{
				imgRepo.remove(existing);
				imgRepo.flush();
				LOGGER.trace("Image for person "+person.getId()+" already exists and was deleted");
			}
			PersonImage img = new PersonImage();
			img.setData(imageData);
			img.setContentType(contentType);
			
			//retrieve the person we have to assign the image to
			Person personEntity = repository.find(person.getId());
			if(personEntity == null)
			{
				LOGGER.error("Cannot save image for nonexistent person "+person.getId());
				throw new VerificationException("Person does not exists");
			}
			img.setPerson(personEntity);
			//save the image
			imgRepo.save(img);
			LOGGER.debug("Person image saved for person: "+person.getId());
		} catch(VerificationException ex)	{
			LOGGER.debug("Invalid input was supplied by frontend: "+ex.getMessage(),ex);
			throw new PersonServiceException("Invalid input was supplied by frontend: "+ex.getMessage());
		} catch(Exception ex) {
			LOGGER.error("Exception during saving of image for person "+person.getId(),ex);
			throw new PersonServiceException("Person image for person "+person.getId()+" could not be saved");
		}
	}

	@Override
	public PersonImageDto getPersonImage(Long personid) {
		try {
			PersonImage img = imgRepo.getByPersonId(personid);
			if(img!=null)
				LOGGER.trace("Image for person "+personid+" was retrieved successfully");
			else
				LOGGER.trace("Could not retrieve image for person "+personid);
			return PersonImageConverter.convert(img);
		} catch(Exception ex) {
			LOGGER.error("Exception during retrieval of image for person "+personid,ex);
			throw new PersonServiceException("Image for person "+personid+" could not be retrieved");
		}
	}

}
