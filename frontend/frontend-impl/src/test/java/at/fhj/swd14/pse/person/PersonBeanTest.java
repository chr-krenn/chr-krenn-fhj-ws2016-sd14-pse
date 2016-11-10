package at.fhj.swd14.pse.person;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.general.ContextMocker;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;
import org.junit.Assert;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PersonBeanTest {

	@InjectMocks
    private PersonBean unitUnderTest;

    @Mock
    private PersonService personService;

    @Mock
    private UserService userService;
	
    public PersonDto getDummyPerson()
    {
		UserDto myuser = new UserDto(1L);
        myuser.setMail("test@test.de");
        myuser.setPassword("testpassword");
        
        DepartmentDto department = new DepartmentDto(1L);
        department.setName("testdepartment");
        
        PersonDto myperson = new PersonDto(1L);
        myperson.setUser(myuser);
        myperson.setAdditionalMails(new LinkedList<MailaddressDto>());
        myperson.getAdditionalMails().add(new MailaddressDto(1L,"test2@test.de"));
        myperson.setAddress("testaddress");
        myperson.setDepartment(department);
        myperson.setFirstname("firstname");
        myperson.setHobbies(new LinkedList<HobbyDto>());
        myperson.getHobbies().add(new HobbyDto(1L,"testhobby"));
        myperson.setImageUrl("http://testimg.org");
        myperson.setKnowledges(new LinkedList<KnowledgeDto>());
        myperson.getKnowledges().add(new KnowledgeDto(1L,"testknowledge"));
        myperson.setLastname("lastname");
        myperson.setPhonenumbers(new LinkedList<PhonenumberDto>());
        myperson.getPhonenumbers().add(new PhonenumberDto(1L,"0664664664"));
        myperson.setPlace("testplace");
        myperson.setStatus(new StatusDto("Online"));
        return myperson;
    }
    
    @Test
    public void testPersonByUserId()
    {
    	PersonDto person = getDummyPerson();
    	when(userService.find(1L)).thenReturn(person.getUser());
    	when(personService.findByUser(person.getUser())).thenReturn(person);
    	FacesContext context = ContextMocker.mockFacesContext();
    	ExternalContext extContext = mock(ExternalContext.class);
    	when(context.getExternalContext()).thenReturn(extContext);
    	
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("userId","1");
    	when(extContext.getRequestParameterMap()).thenReturn(paramMap);
    	
    	String path = unitUnderTest.showPersonByUserId();
    	Assert.assertEquals("/protected/personTest", path);
    	Assert.assertNotNull(unitUnderTest.getPerson());
    	PersonDtoTester.assertEquals(person, unitUnderTest.getPerson());

    }
    
    @Test
    public void testLoggedInPerson()
    {
    	PersonDto person = getDummyPerson();
    	when(personService.getLoggedInPerson()).thenReturn(person);
    	String path = unitUnderTest.showLoggedInPerson();
    	Assert.assertEquals("/protected/loggedInPersonTest", path);
    	Assert.assertNotNull(unitUnderTest.getPerson());
    	PersonDtoTester.assertEquals(person, unitUnderTest.getPerson());
    }
    
}
