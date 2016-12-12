package at.fhj.swd14.pse.person;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.contact.Contact;
import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.converter.PersonImageConverter;
import at.fhj.swd14.pse.converter.StatusConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.repository.ContactRepository;
import at.fhj.swd14.pse.repository.PersonImageRepository;
import at.fhj.swd14.pse.repository.PersonRepository;
import at.fhj.swd14.pse.repository.PersonStatusRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

	@InjectMocks
	private PersonServiceImpl service;
	
	@Mock
	private PersonRepository personRepo;
	
	@Mock
	private PersonImageRepository imgRepo;
	
	@Mock
	private UserService userService;
	
	@Mock
	private PersonStatusRepository statusRepo;
	
	@Mock
	private PersonVerifier verifier;
	
	@Mock
	private ContactRepository contactRepo;
	
	private User user;
	private User user2;
	private Person person;
	private Person person2;
	private List<Person> persons;
	private List<Contact> contacts;
	
	
	@Before
	public void setup() throws NamingException
	{
		
        person = PersonTestTools.getDummyPerson();
        user = person.getUser();
        person2 = PersonTestTools.getAnotherDummyPerson();
        user2 = person.getUser();
       
        persons = new ArrayList<Person>();
        persons.add(person);
        persons.add(person2);
        
        contacts = new ArrayList<Contact>();
        contacts = contactRepo.findByPersonId(1L);
        
        
	}
	
	@Test
	public void testFind()
	{
		Mockito.when(personRepo.find(1L)).thenReturn(person);
		PersonDto foundPerson = service.find(person.getId());
		PersonDtoTester.assertEquals(PersonConverter.convert(person), foundPerson);
	}
	
	@Test
	public void testFindByUser()
	{
		Mockito.when(personRepo.findByUserId(1L)).thenReturn(person);
		PersonDto foundPerson = service.findByUser(UserConverter.convert(user));
		PersonDtoTester.assertEquals(PersonConverter.convert(person), foundPerson);
	}
	
	
	@Test
	public void testFindAllUser()
	{
		Mockito.when(personRepo.findAll()).thenReturn(persons);
		Mockito.when(personRepo.findByUserId(1L)).thenReturn(person);
		Mockito.when(contactRepo.findByPersonId(1L)).thenReturn(contacts);

		Collection<PersonDto> persons = service.findAllUser(1L);
		
		for(PersonDto p : persons)
		{
			PersonDtoTester.assertEquals(PersonConverter.convert(person2), p);
		}
	}
	
	@Test(expected=PersonServiceException.class)
	public void testSaveNull()
	{
		service.saveLoggedInPerson(null);
	}
	
	@Test
	public void testSave()
	{
		service.saveLoggedInPerson(PersonConverter.convert(person));
		
		Mockito.verify(personRepo, times(1)).update(Mockito.any(Person.class));
	}
	
	@Test
	public void testFindAllStati()
	{
		List<Status> stati = new LinkedList<Status>();
		stati.add(new Status("online"));
		Mockito.when(statusRepo.findAll()).thenReturn(stati);
		Collection<StatusDto> result = service.findAllStati();
		Assert.assertEquals(1, result.size());
		StatusDtoTester.assertEquals(StatusConverter.convert(stati.get(0)), ((List<StatusDto>)result).get(0));
	}
	
	@Test
	public void testSavePersonImage()
	{
		PersonDto dummyDto = PersonConverter.convert(person);
		byte[] imgData = new byte[1];
		imgData[0]=1;
		String contentType = "image/png";
		
		Mockito.when(personRepo.find(dummyDto.getId())).thenReturn(person);
		
		service.savePersonImage(dummyDto, imgData, contentType);
		
		Mockito.verify(imgRepo,Mockito.times(1)).save(Mockito.any(PersonImage.class));
	}
	
	@Test
	public void testSavePersonImageExists()
	{
		PersonDto dummyDto = PersonConverter.convert(person);
		byte[] imgData = new byte[1];
		imgData[0]=1;
		String contentType = "image/png";
		
		Mockito.when(personRepo.find(dummyDto.getId())).thenReturn(person);
		PersonImage existing = new PersonImage(1L);
		Mockito.when(imgRepo.getByPersonId(person.getId())).thenReturn(existing);
		service.savePersonImage(dummyDto, imgData, contentType);
		Mockito.verify(imgRepo,Mockito.times(1)).remove(existing);
		Mockito.verify(imgRepo,Mockito.times(1)).flush();
		Mockito.verify(imgRepo,Mockito.times(1)).save(Mockito.any(PersonImage.class));
	}
	
	@Test
	public void testGetPersonImage()
	{
		PersonImage img = new PersonImage(1L);
		img.setContentType("image/png");
		img.setData(new byte[1]);
		img.setPerson(person);
		Mockito.when(imgRepo.getByPersonId(1L)).thenReturn(img);
		PersonImageDto dto = service.getPersonImage(1L);
		PersonImageDtoTester.assertEquals(PersonImageConverter.convert(img),dto);
	}
	
	@Test(expected=PersonServiceException.class)
	public void testFindException()
	{
		Mockito.doThrow(Exception.class).when(personRepo).find(Mockito.anyLong());
		service.find(1L);
	}
	
	@Test(expected=PersonServiceException.class)
	public void testFindError()
	{
		Mockito.doThrow(Error.class).when(personRepo).find(Mockito.anyLong());
		service.find(1L);
	}
	
	
	@Test(expected=PersonServiceException.class)
	public void testFindByUserException()
	{
		Mockito.doThrow(Exception.class).when(personRepo).findByUserId(Mockito.anyLong());
		service.findByUser(new UserDto(1L));
	}
	
	@Test(expected=PersonServiceException.class)
	public void testFindByUserError()
	{
		Mockito.doThrow(Error.class).when(personRepo).findByUserId(Mockito.anyLong());
		service.findByUser(new UserDto(1L));
	}
	
	@Test(expected=PersonServiceException.class)
	public void testSaveException()
	{
		Mockito.doThrow(Exception.class).when(verifier).verifyUser(Mockito.any());
		service.saveLoggedInPerson(new PersonDto());
	}
	
	@Test(expected=PersonServiceException.class)
	public void testSaveError()
	{
		Mockito.doThrow(Error.class).when(verifier).verifyUser(Mockito.any());
		service.saveLoggedInPerson(new PersonDto());
	}
	
	
	@Test(expected=PersonServiceException.class)
	public void testStatusException()
	{
		Mockito.doThrow(Exception.class).when(statusRepo).findAll();
		service.findAllStati();
	}
	
	@Test(expected=PersonServiceException.class)
	public void testStatusError()
	{
		Mockito.doThrow(Error.class).when(statusRepo).findAll();
		service.findAllStati();
	}
	
	@Test(expected=PersonServiceException.class)
	public void testImageDataNull()
	{
		service.savePersonImage(new PersonDto(), null, "png");
	}
	
	@Test(expected=PersonServiceException.class)
	public void testImageDataEmpty()
	{
		service.savePersonImage(new PersonDto(), new byte[0], "png");
	}
	
	@Test(expected=PersonServiceException.class)
	public void testImagePersonNull()
	{
		service.savePersonImage(null, new byte[1], "png");
	}
	
	@Test(expected=PersonServiceException.class)
	public void testImageIdNullEmpty()
	{
		service.savePersonImage(new PersonDto(), new byte[1], "png");
	}
	
	@Test(expected=PersonServiceException.class)
	public void testImagePersonNotFoundEmpty()
	{
		Mockito.when(personRepo.find(Mockito.anyLong())).thenReturn(null);
		service.savePersonImage(PersonConverter.convert(person), new byte[1], "png");
	}
	@Test(expected=PersonServiceException.class)
	public void testImageException()
	{
		Mockito.doThrow(Exception.class).when(imgRepo).getByPersonId(Mockito.anyLong());
		service.savePersonImage(PersonConverter.convert(person), new byte[1], "png");
	}
	
	@Test(expected=PersonServiceException.class)
	public void testImageError()
	{
		Mockito.doThrow(Error.class).when(imgRepo).getByPersonId(Mockito.anyLong());
		service.savePersonImage(PersonConverter.convert(person), new byte[1], "png");
	}
	
	@Test
	public void testImageNotFound()
	{
		PersonImageDto dto = service.getPersonImage(null);
		Assert.assertNull(dto);
	}
	
	@Test(expected=PersonServiceException.class)
	public void testGetImageException()
	{
		Mockito.doThrow(Exception.class).when(imgRepo).getByPersonId(Mockito.anyLong());
		service.getPersonImage(null);
	}
	
	@Test(expected=PersonServiceException.class)
	public void testGetImageError()
	{
		Mockito.doThrow(Error.class).when(imgRepo).getByPersonId(Mockito.anyLong());
		service.getPersonImage(null);
	}
}
