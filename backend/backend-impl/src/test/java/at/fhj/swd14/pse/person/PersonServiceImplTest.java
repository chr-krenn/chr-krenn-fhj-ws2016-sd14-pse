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
	
	public Person getDummyPerson()
	{
		User myuser = new User(1L);
        myuser.setMail("test@test.de");
        myuser.setPassword("testpassword");
        
        Department department = new Department(1L);
        department.setName("testdepartment");
        
        Person myperson = new Person(1L,myuser);
        myperson.setAdditionalMails(new LinkedList<Mailaddress>());
        myperson.getAdditionalMails().add(new Mailaddress("test2@test.de"));
        myperson.setAddress("testaddress");
        myperson.setDepartment(department);
        myperson.setFirstname("firstname");
        myperson.setHobbies(new LinkedList<Hobby>());
        myperson.getHobbies().add(new Hobby("testhobby"));
        myperson.setImageUrl("http://testimg.org");
        myperson.setKnowledges(new LinkedList<Knowledge>());
        myperson.getKnowledges().add(new Knowledge("testknowledge"));
        myperson.setLastname("lastname");
        myperson.setNumbers(new LinkedList<Phonenumber>());
        myperson.getNumbers().add(new Phonenumber("0664664664"));
        myperson.setPlace("testplace");
        myperson.setStatus(new Status("Online"));
        return myperson;
	}
	
	@Before
	public void setup() throws NamingException
	{
		
        service = new PersonServiceImpl();
        service.repository = Mockito.mock(PersonRepository.class);
        
        person = getDummyPerson();
        user = person.getUser();
        
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
