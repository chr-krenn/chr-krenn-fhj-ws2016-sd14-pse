package at.fhj.swd14.pse.person.tools;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.department.DepartmentService;
import at.fhj.swd14.pse.person.CommonPersonBeanTest;
import at.fhj.swd14.pse.person.CommonPersonBeanTestData;
import at.fhj.swd14.pse.person.HobbyDto;
import at.fhj.swd14.pse.person.KnowledgeDto;
import at.fhj.swd14.pse.person.MailaddressDto;
import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.person.PhonenumberDto;
import at.fhj.swd14.pse.person.StatusDto;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserDtoTester;
import at.fhj.swd14.pse.user.UserService;

public class LoggedInPersonHandlerTest {
	
	private LoggedInPersonPageHandler handler;
	private PersonBean bean;
	private UserService userService;
	private PersonService personService;
	private DepartmentService departmentService;
	private PersonVerifier verifier;
    private ExternalContext extContext;
    private PersonDto person;
    private List<StatusDto> stati;
    private List<DepartmentDto> deps;
    private UserDto user;
	
	@Before
	public void setup()
	{
		userService = Mockito.mock(UserService.class);
		personService = Mockito.mock(PersonService.class);
		departmentService = Mockito.mock(DepartmentService.class);
		verifier = Mockito.mock(PersonVerifier.class);
		bean = Mockito.mock(PersonBean.class);
		Mockito.when(bean.getUserService()).thenReturn(userService);
		Mockito.when(bean.getPersonService()).thenReturn(personService);
		Mockito.when(bean.getDepartmentService()).thenReturn(departmentService);
		Mockito.when(bean.getVerifier()).thenReturn(verifier);
		handler = new LoggedInPersonPageHandler(bean);
		CommonPersonBeanTestData data = CommonPersonBeanTest.setupTest(userService, personService, departmentService);
    	person = data.getPerson();
    	extContext = data.getExtContext();
    	stati = data.getStati();
    	deps = data.getDeps();
		user=person.getUser();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBeanNull()
	{
		new LoggedInPersonPageHandler(null);
	}
	
	@Test
    public void testLoggedInPerson()
    {    	
    	String path = handler.showLoggedInPerson();
    	Assert.assertEquals("/user", path);
    	Mockito.verify(bean,Mockito.times(1)).setPerson(person);
    	Mockito.verify(bean,Mockito.times(1)).setStati(stati);
    	Mockito.verify(bean,Mockito.times(1)). setDepartments(deps);
    }
	
	@Test
	public void testAlreadyLoaded()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		String path = handler.showLoggedInPerson();
    	Assert.assertEquals("/user", path);
    	Mockito.verify(bean,Mockito.times(0)).setPerson(person);
    	Mockito.verify(bean,Mockito.times(1)).setStati(stati);
    	Mockito.verify(bean,Mockito.times(1)). setDepartments(deps);
	}
	
	@Test
	public void testPersonNotFound()
	{
		when(userService.find(1L)).thenReturn(null);
		handler.showLoggedInPerson();
    	Mockito.verify(bean,Mockito.times(1)).setPerson((PersonDto)Mockito.notNull());
	}
	
	@Test
	public void testCreateInvalidLoggedInPerson()
	{
		Mockito.when(verifier.verifyName()).thenReturn(false);
		String path = handler.createLoggedInPerson();
		Assert.assertEquals("/user", path);
		Mockito.verify(personService,Mockito.times(0)).saveLoggedInPerson(Mockito.any());
	}
	
	@Test
	public void testCreateLoggedInPerson()
	{
		Mockito.when(verifier.verifyName()).thenReturn(true);
		person.setUser(null);
		person.setStatus(null);
		Mockito.when(bean.getPerson()).thenReturn(person);
		String path = handler.createLoggedInPerson();
		Assert.assertEquals("/user", path);
		Assert.assertNotNull(person.getUser());
		UserDtoTester.assertEquals(user, person.getUser());
		Assert.assertNotNull(person.getStatus());
		Assert.assertEquals("online", person.getStatus().getName());
		Mockito.verify(personService,Mockito.times(1)).saveLoggedInPerson(person);
	}
	
	@Test
	public void testSaveData()
	{
		//this really is just for code coverage,there is nothing to verify...
		handler.saveData();
	}
	
	@Test
	public void testClearImageUrl()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.clearImgUrl();
		Assert.assertNull(person.getImageUrl());
	}
	
	@Test
	public void testSaveInvalidPerson()
	{
		Mockito.when(verifier.verifyPerson()).thenReturn(false);
		handler.savePerson();
		Mockito.verify(personService,Mockito.times(0)).saveLoggedInPerson(Mockito.any());
	}
	
	@Test
	public void testSavePerson()
	{
		Mockito.when(verifier.verifyPerson()).thenReturn(true);
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.savePerson();
		Mockito.verify(personService,Mockito.times(1)).saveLoggedInPerson(person);
	}
	
	@Test
	public void testMailExists()
	{
		Mockito.when(bean.getNewMail()).thenReturn(person.getAdditionalMails().get(0).getValue());
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.addMail();
		Mockito.verify(bean,Mockito.times(1)).setNewMail(null);
		Mockito.verify(verifier,Mockito.times(0)).verifyMail(Mockito.any());
	}
	
	@Test
	public void testInvalidMail()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		Mockito.when(bean.getNewMail()).thenReturn("hallo@hallo.at");
		Mockito.when(verifier.verifyMail(Mockito.any())).thenReturn(false);
		handler.addMail();
		Mockito.verify(bean,Mockito.times(0)).setNewMail(null);
	}
	
	@Test
	public void testAddMail()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		Mockito.when(bean.getNewMail()).thenReturn("hallo@hallo.at");
		Mockito.when(verifier.verifyMail(Mockito.any())).thenReturn(true);
		handler.addMail();
		Mockito.verify(bean,Mockito.times(1)).setNewMail(null);
	}
	
	@Test
	public void testKnowledgeExists()
	{
		Mockito.when(bean.getNewKnowledge()).thenReturn(person.getKnowledges().get(0).getValue());
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.addKnowledge();
		Mockito.verify(bean,Mockito.times(1)).setNewKnowledge(null);
		Mockito.verify(verifier,Mockito.times(0)).verifyKnowledge(Mockito.any());
	}
	
	@Test
	public void testInvalidKnowledge()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		Mockito.when(bean.getNewKnowledge()).thenReturn("hallo");
		Mockito.when(verifier.verifyKnowledge(Mockito.any())).thenReturn(false);
		handler.addKnowledge();
		Mockito.verify(bean,Mockito.times(0)).setNewKnowledge(null);
	}
	
	@Test
	public void testAddKnowledge()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		Mockito.when(bean.getNewKnowledge()).thenReturn("hallo");
		Mockito.when(verifier.verifyKnowledge(Mockito.any())).thenReturn(true);
		handler.addKnowledge();
		Mockito.verify(bean,Mockito.times(1)).setNewKnowledge(null);
	}
	
	@Test
	public void testHobbyExists()
	{
		Mockito.when(bean.getNewHobby()).thenReturn(person.getHobbies().get(0).getValue());
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.addHobby();
		Mockito.verify(bean,Mockito.times(1)).setNewHobby(null);
		Mockito.verify(verifier,Mockito.times(0)).verifyHobby(Mockito.any());
	}
	
	@Test
	public void testInvalidHobby()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		Mockito.when(bean.getNewHobby()).thenReturn("hallo");
		Mockito.when(verifier.verifyHobby(Mockito.any())).thenReturn(false);
		handler.addHobby();
		Mockito.verify(bean,Mockito.times(0)).setNewHobby(null);
	}
	
	@Test
	public void testAddHobby()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		Mockito.when(bean.getNewHobby()).thenReturn("hallo");
		Mockito.when(verifier.verifyHobby(Mockito.any())).thenReturn(true);
		handler.addHobby();
		Mockito.verify(bean,Mockito.times(1)).setNewHobby(null);
	}
	
	@Test
	public void testNumberExists()
	{
		Mockito.when(bean.getNewNumber()).thenReturn(person.getPhonenumbers().get(0).getValue());
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.addNumber();
		Mockito.verify(bean,Mockito.times(1)).setNewNumber(null);
		Mockito.verify(verifier,Mockito.times(0)).verifyNumber(Mockito.any());
	}
	
	@Test
	public void testInvalidNumber()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		Mockito.when(bean.getNewNumber()).thenReturn("hallo");
		Mockito.when(verifier.verifyNumber(Mockito.any())).thenReturn(false);
		handler.addNumber();
		Mockito.verify(bean,Mockito.times(0)).setNewNumber(null);
	}
	
	@Test
	public void testAddNumber()
	{
		Mockito.when(bean.getPerson()).thenReturn(person);
		Mockito.when(bean.getNewNumber()).thenReturn("hallo");
		Mockito.when(verifier.verifyNumber(Mockito.any())).thenReturn(true);
		handler.addNumber();
		Mockito.verify(bean,Mockito.times(1)).setNewNumber(null);
	}
	
	@Test
	public void testRemoveMail()
	{
		person.getAdditionalMails().add(new MailaddressDto(null, "hallo@hallo.de"));
		Map<String,String> params = new HashMap<>();
		params.put("value","hallo@hallo.de");
		Mockito.when(extContext.getRequestParameterMap()).thenReturn(params);
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.removeMail();
		Assert.assertEquals(1, person.getAdditionalMails().size());
	}
	
	@Test
	public void testRemoveKnowledge()
	{
		person.getKnowledges().add(new KnowledgeDto(null, "hallo"));
		Map<String,String> params = new HashMap<>();
		params.put("value","hallo");
		Mockito.when(extContext.getRequestParameterMap()).thenReturn(params);
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.removeKnowledge();
		Assert.assertEquals(1, person.getKnowledges().size());
	}
	
	@Test
	public void testRemoveHobby()
	{
		person.getHobbies().add(new HobbyDto(null, "hallo"));
		Map<String,String> params = new HashMap<>();
		params.put("value","hallo");
		Mockito.when(extContext.getRequestParameterMap()).thenReturn(params);
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.removeHobby();
		Assert.assertEquals(1, person.getHobbies().size());
	}
	
	@Test
	public void testRemoveNumber()
	{
		person.getPhonenumbers().add(new PhonenumberDto(null, "hallo"));
		Map<String,String> params = new HashMap<>();
		params.put("value","hallo");
		Mockito.when(extContext.getRequestParameterMap()).thenReturn(params);
		Mockito.when(bean.getPerson()).thenReturn(person);
		handler.removeNumber();
		Assert.assertEquals(1, person.getPhonenumbers().size());
	}
	
	
	@Test
	public void testFileUpload() throws IOException
	{
		FileUploadEvent event = Mockito.mock(FileUploadEvent.class);
		byte[] filedata = doFileUpload(event);
		verifyFileUpload(event, filedata, 0);
	}
	
	@Test
	public void testFileUploadFailed() throws IOException
	{
		FileUploadEvent event = Mockito.mock(FileUploadEvent.class);
		byte[] filedata = doFileUpload(event);
		Mockito.doThrow(IOException.class).when(extContext).redirect(Mockito.anyString());
		verifyFileUpload(event, filedata, 1);
	}

	private byte[] doFileUpload(FileUploadEvent event) {
		UploadedFile file = Mockito.mock(UploadedFile.class);
		Mockito.when(event.getFile()).thenReturn(file);
		byte[] filedata=new byte[1];
		Mockito.when(file.getContents()).thenReturn(filedata);
		Mockito.when(file.getContentType()).thenReturn("png");
		Mockito.when(bean.getPerson()).thenReturn(person);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(extContext.getRequest()).thenReturn(request);
		Mockito.when(request.getRequestURI()).thenReturn("http://test");
		return filedata;
	}

	private void verifyFileUpload(FileUploadEvent event, byte[] filedata, int times) throws IOException {
		handler.handleFileUpload(event);
		Mockito.verify(personService,Mockito.times(1)).savePersonImage(person, filedata, "png");
		Mockito.verify(extContext,Mockito.times(1)).redirect("http://test");
		Mockito.verify(bean,Mockito.times(times)).growl(Mockito.anyString(),Mockito.anyString());
		Assert.assertEquals(person.getImageUrl(), "/swd14-fe/personImage?id="+person.getId());
	}
}
