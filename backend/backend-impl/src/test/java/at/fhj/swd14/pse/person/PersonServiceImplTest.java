package at.fhj.swd14.pse.person;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.converter.PersonImageConverter;
import at.fhj.swd14.pse.converter.StatusConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.repository.PersonImageRepository;
import at.fhj.swd14.pse.repository.PersonRepository;
import at.fhj.swd14.pse.repository.PersonStatusRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserService;
import org.junit.Assert;

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
	
	private User user;
	private Person person;
	private List<Person> persons;
	
	
	
	@Before
	public void setup() throws NamingException
	{
		
        person = PersonTestTools.getDummyPerson();
        user = person.getUser();
        
        persons = new ArrayList<Person>();
        persons.add(person);
        
        
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
		Collection<PersonDto> persons = service.findAllUser();
		for(PersonDto person : persons)
		{
			PersonDtoTester.assertEquals(person, person);
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
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
	
}
