package at.fhj.swd14.pse.person;

import java.security.Principal;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.general.ContextMocker;
import at.fhj.swd14.pse.repository.DepartmentRepository;
import at.fhj.swd14.pse.repository.HobbyRepository;
import at.fhj.swd14.pse.repository.KnowledgeRepository;
import at.fhj.swd14.pse.repository.MailaddressRepository;
import at.fhj.swd14.pse.repository.PersonStatusRepository;
import at.fhj.swd14.pse.repository.PhonenumberRepository;
import at.fhj.swd14.pse.repository.UserRepository;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class PersonVerifierTest {

	@InjectMocks
	private PersonVerifier verifier;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private PersonStatusRepository statusRepo;
	
	@Mock 
	private DepartmentRepository departmentRepo;
	
	@Mock
	private PhonenumberRepository phonenumberRepo;
	
	@Mock
	private MailaddressRepository mailRepo;
	
	@Mock
	private HobbyRepository hobbyRepo;
	
	@Mock
	private KnowledgeRepository knowledgeRepo;
	
	private Person dummyPerson;
	private PersonDto dummyPersonDto;
	
	@Before
	public void setup()
	{
		dummyPerson = PersonTestTools.getDummyPerson();
		dummyPersonDto = PersonConverter.convert(dummyPerson);
		Mockito.when(userRepo.find(1L)).thenReturn(dummyPerson.getUser());
		FacesContext context = ContextMocker.mockFacesContext();
		ExternalContext extContext = Mockito.mock(ExternalContext.class);
		Mockito.when(context.getExternalContext()).thenReturn(extContext);
		Principal principal = Mockito.mock(Principal.class);
		Mockito.when(extContext.getUserPrincipal()).thenReturn(principal);
		//TODO: Mockito.when(principal.getId()).thenReturn(1L);
		Mockito.when(statusRepo.findByName("online")).thenReturn(dummyPerson.getStatus());
		Mockito.when(departmentRepo.find(1L)).thenReturn(dummyPerson.getDepartment());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUserNull()
	{
		dummyPersonDto.setUser(null);
		verifier.verifyUser(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUserIdNull()
	{
		dummyPersonDto.getUser().setId(null);
		verifier.verifyUser(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUserNotFound()
	{
		Mockito.when(userRepo.find(1L)).thenReturn(null);
		verifier.verifyUser(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUserNotLoggedIn()
	{
		dummyPersonDto.getUser().setId(2L);
		dummyPerson.getUser().setId(2L);
		Mockito.when(userRepo.find(2L)).thenReturn(dummyPerson.getUser());
		verifier.verifyUser(dummyPersonDto);
	}
	
	@Test
	public void testUser()
	{
		verifier.verifyUser(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStatusNull()
	{
		dummyPersonDto.setStatus(null);
		verifier.verifyStatus(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStatusNameNull()
	{
		dummyPersonDto.getStatus().setName(null);
		verifier.verifyStatus(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStatusNotFound()
	{
		Mockito.when(statusRepo.findByName("online")).thenReturn(null);
		verifier.verifyStatus(dummyPersonDto);
	}
	
	@Test
	public void testStatus()
	{
		verifier.verifyStatus(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFirstnameNull()
	{
		dummyPersonDto.setFirstname(null);
		verifier.verifyNotNull(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFirstnameEmpty()
	{
		dummyPersonDto.setFirstname("");
		verifier.verifyNotNull(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLastnameNull()
	{
		dummyPersonDto.setLastname(null);
		verifier.verifyNotNull(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLastnameEmpty()
	{
		dummyPersonDto.setLastname("");
		verifier.verifyNotNull(dummyPersonDto);
	}
	
	@Test
	public void testNotNull()
	{
		verifier.verifyNotNull(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepartmentNotFound()
	{
		Mockito.when(departmentRepo.find(1L)).thenReturn(null);
		verifier.verifyDepartment(dummyPersonDto);
	}
	
	@Test
	public void testDepartmentEmpty()
	{
		dummyPersonDto.setDepartment(null);
		verifier.verifyDepartment(dummyPersonDto);
	}
	
	@Test
	public void testDepartment()
	{
		verifier.verifyDepartment(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPhonenumberWrong()
	{
		dummyPersonDto.getPhonenumbers().get(0).setId(1L);
		verifier.correlateNumbers(dummyPersonDto);
	}
	
	@Test
	public void testPhonenumberExists()
	{
		Phonenumber dummyNumber = new Phonenumber(1L);
		dummyNumber.setValue(dummyPersonDto.getPhonenumbers().get(0).getValue());
		Mockito.when(phonenumberRepo.findByValue(dummyPersonDto.getId(), dummyPersonDto.getPhonenumbers().get(0).getValue()))
			.thenReturn(dummyNumber);
		verifier.correlateNumbers(dummyPersonDto);
		Assert.assertEquals(dummyNumber.getId(), dummyPersonDto.getPhonenumbers().get(0).getId());
	}
	
	@Test
	public void testPhonenumber()
	{
		verifier.correlateNumbers(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMailaddressWrong()
	{
		dummyPersonDto.getAdditionalMails().get(0).setId(1L);
		verifier.correlateMails(dummyPersonDto);
	}
	
	@Test
	public void testMailaddressExists()
	{
		Mailaddress dummymail = new Mailaddress(1L);
		dummymail.setValue(dummyPersonDto.getAdditionalMails().get(0).getValue());
		Mockito.when(mailRepo.findByValue(dummyPersonDto.getId(), dummyPersonDto.getAdditionalMails().get(0).getValue()))
			.thenReturn(dummymail);
		verifier.correlateMails(dummyPersonDto);
		Assert.assertEquals(dummymail.getId(), dummyPersonDto.getAdditionalMails().get(0).getId());
	}
	
	@Test
	public void testMailaddress()
	{
		verifier.correlateMails(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHobbyWrong()
	{
		dummyPersonDto.getHobbies().get(0).setId(1L);
		verifier.correlateHobbies(dummyPersonDto);
	}
	
	@Test
	public void testHobbyExists()
	{
		Hobby dummyhobby = new Hobby(1L);
		dummyhobby.setValue(dummyPersonDto.getHobbies().get(0).getValue());
		Mockito.when(hobbyRepo.findByValue(dummyPersonDto.getId(), dummyPersonDto.getHobbies().get(0).getValue()))
			.thenReturn(dummyhobby);
		verifier.correlateHobbies(dummyPersonDto);
		Assert.assertEquals(dummyhobby.getId(), dummyPersonDto.getHobbies().get(0).getId());
	}
	
	@Test
	public void testHobby()
	{
		verifier.correlateHobbies(dummyPersonDto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testKnowledgeWrong()
	{
		dummyPersonDto.getKnowledges().get(0).setId(1L);
		verifier.correlateKnowledges(dummyPersonDto);
	}
	
	@Test
	public void testKnowledgeExists()
	{
		Knowledge dummyKnowledge = new Knowledge(1L);
		dummyKnowledge.setValue(dummyPersonDto.getKnowledges().get(0).getValue());
		Mockito.when(knowledgeRepo.findByValue(dummyPersonDto.getId(), dummyPersonDto.getKnowledges().get(0).getValue()))
			.thenReturn(dummyKnowledge);
		verifier.correlateKnowledges(dummyPersonDto);
		Assert.assertEquals(dummyKnowledge.getId(), dummyPersonDto.getKnowledges().get(0).getId());
	}
	
	@Test
	public void testKnowledge()
	{
		verifier.correlateKnowledges(dummyPersonDto);
	}
	
	
	
	
}
