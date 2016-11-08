package at.fhj.swd14.pse.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.repository.PersonRepository;
import at.fhj.swd14.pse.user.User;

public class PersonServiceImplTest {

	private PersonServiceImpl service;
	private User user;
	private Person person;
	private List<Person> persons;
	
	@Before
	public void setup() throws NamingException
	{
		
        service = new PersonServiceImpl();
        service.repository = Mockito.mock(PersonRepository.class);
        
        user = new User(1L);
        user.setMail("test@test.de");
        user.setPassword("testpassword");
        
        Department department = new Department(1L);
        department.setName("testdepartment");
        
        person = new Person(1L,user);
        person.setAdditionalMails(new LinkedList<Mailaddress>());
        person.getAdditionalMails().add(new Mailaddress("test2@test.de"));
        person.setAddress("testaddress");
        person.setDepartment(department);
        person.setFirstname("firstname");
        person.setHobbies(new LinkedList<Hobby>());
        person.getHobbies().add(new Hobby("testhobby"));
        person.setImageUrl("http://testimg.org");
        person.setKnowledges(new LinkedList<Knowledge>());
        person.getKnowledges().add(new Knowledge("testknowledge"));
        person.setLastname("lastname");
        person.setNumbers(new LinkedList<Phonenumber>());
        person.getNumbers().add(new Phonenumber("0664664664"));
        person.setPlace("testplace");
        person.setStatus(new Status("Online"));
        
        persons = new ArrayList<Person>();
        persons.add(person);
        
        
	}
	
	@Test
	public void testFind()
	{
		Mockito.when(service.repository.find(Mockito.anyLong())).thenReturn(person);
		PersonDto foundPerson = service.find(person.getId());
		PersonDtoTester.assertEquals(PersonConverter.convert(person), foundPerson);
	}
	
	@Test
	public void testFindByUser()
	{
		Mockito.when(service.repository.findByUserId(Mockito.anyLong())).thenReturn(person);
		PersonDto foundPerson = service.findByUser(UserConverter.convert(user));
		PersonDtoTester.assertEquals(PersonConverter.convert(person), foundPerson);
	}
	
	@Test
	public void testFindAllUser()
	{
		Mockito.when(service.repository.findAll()).thenReturn(persons);
		Collection<PersonDto> persons = service.findAllUser();
		for(PersonDto person : persons)
		{
			PersonDtoTester.assertEquals(person, person);
		}
	}
	
}
