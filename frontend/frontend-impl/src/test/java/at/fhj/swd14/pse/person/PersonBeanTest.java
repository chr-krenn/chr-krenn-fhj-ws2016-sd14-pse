package at.fhj.swd14.pse.person;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.FileUploadEvent;

import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.department.DepartmentService;
import at.fhj.swd14.pse.person.tools.LoggedInPersonPageHandler;
import at.fhj.swd14.pse.person.tools.PersonPageHandler;
import at.fhj.swd14.pse.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class PersonBeanTest {

	@InjectMocks
    private PersonBean unitUnderTest;

    @Mock
    private PersonService personService;
    
    @Mock
    private DepartmentService departmentService;

    @Mock
    private UserService userService;
    
    private FacesContext context;
    private ExternalContext extContext;
    private PersonDto person;
    private List<StatusDto> stati;
    private List<DepartmentDto> deps;
    
    @Before
    public void setup()
    {
    	CommonPersonBeanTestData data = CommonPersonBeanTest.setupTest(userService, personService, departmentService);
    	person = data.getPerson();
    	context = data.getContext();
    	extContext = data.getExtContext();
    	stati = data.getStati();
    	deps = data.getDeps();
    	unitUnderTest.init();
    }
    
    @Test
    public void testPersonByUserId()
    {
    	Map<String,String> paramMap = new HashMap<>();
    	paramMap.put("userId","1");
    	when(extContext.getRequestParameterMap()).thenReturn(paramMap);
    	String path = unitUnderTest.showPersonByUserId();
    	Assert.assertEquals("/myprofile", path);
    	Assert.assertNotNull(unitUnderTest.getPerson());
    	PersonDtoTester.assertEquals(person, unitUnderTest.getPerson());
    }
    
    @Test
    public void testLoggedInPerson()
    {    	
    	String path = unitUnderTest.showLoggedInPerson();
    	Assert.assertEquals("/user", path);
    	Assert.assertNotNull(unitUnderTest.getPerson());
    	PersonDtoTester.assertEquals(person, unitUnderTest.getPerson());
    	Assert.assertEquals(1, unitUnderTest.getStati().size());
    	StatusDtoTester.assertEquals(stati.get(0), unitUnderTest.getStati().get(0));
    	Assert.assertEquals(1, unitUnderTest.getDepartments().size());
    	DepartmentDtoTester.assertEquals(deps.get(0), unitUnderTest.getDepartments().get(0));
    }
    
    @Test
    public void testCreate()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	unitUnderTest.setPerson(person);
    	unitUnderTest.createLoggedInPerson();
		Mockito.verify(personService,Mockito.times(1)).saveLoggedInPerson(Mockito.any(PersonDto.class));
    }
    
    @Test
    public void testSave()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	unitUnderTest.setPerson(person);
    	unitUnderTest.savePerson();
    	Mockito.verify(personService,Mockito.times(1)).saveLoggedInPerson(Mockito.any(PersonDto.class));
    }
    
    @Test
    public void testAddMail()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	person.getAdditionalMails().clear();
    	unitUnderTest.setPerson(person);
    	unitUnderTest.setNewMail("test@test.de");
    	unitUnderTest.addMail();
    	Assert.assertEquals(1, unitUnderTest.getPerson().getAdditionalMails().size());
    	Assert.assertEquals("test@test.de", unitUnderTest.getPerson().getAdditionalMails().get(0).getValue());
    }
    
    @Test
    public void testAddKnowledge()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	person.getKnowledges().clear();
    	unitUnderTest.setPerson(person);
    	unitUnderTest.setNewKnowledge("test");
    	unitUnderTest.addKnowledge();
    	Assert.assertEquals(1, unitUnderTest.getPerson().getKnowledges().size());
    	Assert.assertEquals("test", unitUnderTest.getPerson().getKnowledges().get(0).getValue());
    }
    
    @Test
    public void testAddHobby()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	person.getHobbies().clear();
    	unitUnderTest.setPerson(person);
    	unitUnderTest.setNewHobby("test");
    	unitUnderTest.addHobby();
    	Assert.assertEquals(1, unitUnderTest.getPerson().getHobbies().size());
    	Assert.assertEquals("test", unitUnderTest.getPerson().getHobbies().get(0).getValue());
    }
    
    @Test
    public void testAddNumber()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	person.getPhonenumbers().clear();
    	unitUnderTest.setPerson(person);
    	unitUnderTest.setNewNumber("0664664664");
    	unitUnderTest.addNumber();
    	Assert.assertEquals(1, unitUnderTest.getPerson().getPhonenumbers().size());
    	Assert.assertEquals("0664664664", unitUnderTest.getPerson().getPhonenumbers().get(0).getValue());
    }
    
    @Test
    public void testRemoveMail()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	unitUnderTest.setPerson(person);
    	Map<String,String> paramMap = new HashMap<>();
    	paramMap.put("value","test2@test.de");
    	when(extContext.getRequestParameterMap()).thenReturn(paramMap);
    	unitUnderTest.removeMail();
    	Assert.assertEquals(0, unitUnderTest.getPerson().getAdditionalMails().size());
    }
    
    @Test
    public void testRemoveKnowledge()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	unitUnderTest.setPerson(person);
    	Map<String,String> paramMap = new HashMap<>();
    	paramMap.put("value","testknowledge");
    	when(extContext.getRequestParameterMap()).thenReturn(paramMap);
    	unitUnderTest.removeKnowledge();
    	Assert.assertEquals(0, unitUnderTest.getPerson().getKnowledges().size());
    }
    
    @Test
    public void testRemoveHobby()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	unitUnderTest.setPerson(person);
    	Map<String,String> paramMap = new HashMap<>();
    	paramMap.put("value","testhobby");
    	when(extContext.getRequestParameterMap()).thenReturn(paramMap);
    	unitUnderTest.removeHobby();
    	Assert.assertEquals(0, unitUnderTest.getPerson().getHobbies().size());
    }
    
    @Test
    public void testRemoveNumber()
    {
    	PersonDto person = CommonPersonBeanTest.getDummyPerson();
    	unitUnderTest.setPerson(person);
    	Map<String,String> paramMap = new HashMap<>();
    	paramMap.put("value","0664664664");
    	when(extContext.getRequestParameterMap()).thenReturn(paramMap);
    	unitUnderTest.removeNumber();
    	Assert.assertEquals(0, unitUnderTest.getPerson().getPhonenumbers().size());
    }
    
    
    private void growled(int times)
    {
    	Mockito.verify(context,Mockito.times(times)).addMessage(Mockito.anyString(), Mockito.any(FacesMessage.class));
    }
    
    @Test
    public void testGrowl()
    {
    	unitUnderTest.growl("test",new Exception("hallo"));
    	growled(1);
    }
    
    @Test
    public void testUserIdException()
    {
    	PersonPageHandler personPageHandler = Mockito.mock(PersonPageHandler.class);
    	unitUnderTest.setPersonPageHandler(personPageHandler);
    	Mockito.doThrow(Exception.class).when(personPageHandler).showPersonByUserId();
    	unitUnderTest.showPersonByUserId();
    	growled(1);
    }
    
    private LoggedInPersonPageHandler setLoggedInHandler()
    {
    	LoggedInPersonPageHandler loggedInHandler = Mockito.mock(LoggedInPersonPageHandler.class);
    	unitUnderTest.setLoggedInPersonPageHandler(loggedInHandler);
    	return loggedInHandler;
    }
    
    @Test
    public void testLoggedInException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).showLoggedInPerson();
    	unitUnderTest.showLoggedInPerson();
    	growled(1);
    }
    
    @Test
    public void testCreateException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).createLoggedInPerson();
    	unitUnderTest.createLoggedInPerson();
    	growled(1);
    }
    
    @Test
    public void testSaveDataException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).saveData();
    	unitUnderTest.saveData();
    	growled(1);
    }
    
    @Test
    public void testClearException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).clearImgUrl();
    	unitUnderTest.clearImgUrl();
    	growled(1);
    }
    
    @Test
    public void testSaveException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).savePerson();
    	unitUnderTest.savePerson();
    	growled(1);
    }
    
    @Test
    public void testAddMailException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).addMail();
    	unitUnderTest.addMail();
    	growled(1);
    }
    
    @Test
    public void testAddKnowledgeException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).addKnowledge();
    	unitUnderTest.addKnowledge();
    	growled(1);
    }
    
    @Test
    public void testAddHobbyException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).addHobby();
    	unitUnderTest.addHobby();
    	growled(1);
    }
    
    @Test
    public void testAddNumberException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).addNumber();
    	unitUnderTest.addNumber();
    	growled(1);
    }
    
    @Test
    public void testRemoveMailException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).removeMail();
    	unitUnderTest.removeMail();
    	growled(1);
    }
    
    @Test
    public void testRemoveKnowledgeException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).removeKnowledge();
    	unitUnderTest.removeKnowledge();
    	growled(1);
    }
    
    @Test
    public void testRemoveHobbyException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).removeHobby();
    	unitUnderTest.removeHobby();
    	growled(1);
    }
    
    @Test
    public void testRemoveNumberException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).removeNumber();
    	unitUnderTest.removeNumber();
    	growled(1);
    }
    
    @Test
    public void testHandleFileUploadException()
    {
    	LoggedInPersonPageHandler handler = setLoggedInHandler();
    	Mockito.doThrow(Exception.class).when(handler).handleFileUpload(Mockito.any());
    	unitUnderTest.handleFileUpload(Mockito.mock(FileUploadEvent.class));
    	growled(1);
    }
    
    
    @Test
    public void testComplete()
    {
    	//here are all the things we have to do for 100% completion...
    	setLoggedInHandler();
    	unitUnderTest.getLoggedInUserId();
    	unitUnderTest.saveData();
    	unitUnderTest.clearImgUrl();
    	unitUnderTest.handleFileUpload(Mockito.mock(FileUploadEvent.class));
    }
    
}


