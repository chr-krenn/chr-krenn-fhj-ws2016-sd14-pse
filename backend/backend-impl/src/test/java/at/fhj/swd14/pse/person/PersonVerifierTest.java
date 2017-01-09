package at.fhj.swd14.pse.person;


import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.repository.internal.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonVerifierTest {

    @InjectMocks
    private PersonVerifier verifier;

    @Mock
    private UserRepositoryImpl userRepo;

    @Mock
    private PersonStatusRepositoryImpl statusRepo;

    @Mock
    private DepartmentRepositoryImpl departmentRepo;

    @Mock
    private PhonenumberRepositoryImpl phonenumberRepo;

    @Mock
    private MailaddressRepositoryImpl mailRepo;

    @Mock
    private HobbyRepositoryImpl hobbyRepo;

    @Mock
    private KnowledgeRepositoryImpl knowledgeRepo;

    private Person dummyPerson;
    private PersonDto dummyPersonDto;

    @Before
    public void setup() {
        dummyPerson = PersonTestTools.getDummyPerson();
        dummyPersonDto = PersonConverter.convert(dummyPerson);
        Mockito.when(userRepo.find(1L)).thenReturn(dummyPerson.getUser());
        Mockito.when(statusRepo.findByName("online")).thenReturn(dummyPerson.getStatus());
        Mockito.when(departmentRepo.find(1L)).thenReturn(dummyPerson.getDepartment());
    }

    @Test(expected = VerificationException.class)
    public void testUserNull() {
        dummyPersonDto.setUser(null);
        verifier.verifyUser(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testUserIdNull() {
        dummyPersonDto.getUser().setId(null);
        verifier.verifyUser(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testUserNotFound() {
        Mockito.when(userRepo.find(1L)).thenReturn(null);
        verifier.verifyUser(dummyPersonDto);
    }


    @Test
    public void testUser() {
        verifier.verifyUser(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testStatusNull() {
        dummyPersonDto.setStatus(null);
        verifier.verifyStatus(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testStatusNameNull() {
        dummyPersonDto.getStatus().setName(null);
        verifier.verifyStatus(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testStatusNotFound() {
        Mockito.when(statusRepo.findByName("online")).thenReturn(null);
        verifier.verifyStatus(dummyPersonDto);
    }

    @Test
    public void testStatus() {
        verifier.verifyStatus(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testFirstnameNull() {
        dummyPersonDto.setFirstname(null);
        verifier.verifyNotNull(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testFirstnameEmpty() {
        dummyPersonDto.setFirstname("");
        verifier.verifyNotNull(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testLastnameNull() {
        dummyPersonDto.setLastname(null);
        verifier.verifyNotNull(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testLastnameEmpty() {
        dummyPersonDto.setLastname("");
        verifier.verifyNotNull(dummyPersonDto);
    }

    @Test
    public void testNotNull() {
        verifier.verifyNotNull(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testDepartmentNotFound() {
        Mockito.when(departmentRepo.find(1L)).thenReturn(null);
        verifier.verifyDepartment(dummyPersonDto);
    }

    @Test
    public void testDepartmentEmpty() {
        dummyPersonDto.setDepartment(null);
        verifier.verifyDepartment(dummyPersonDto);
    }

    @Test
    public void testDepartment() {
        verifier.verifyDepartment(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testPhonenumberWrong() {
        dummyPersonDto.getPhonenumbers().get(0).setId(1L);
        verifier.correlateNumbers(dummyPersonDto);
    }

    @Test
    public void testPhonenumberExists() {
        Phonenumber dummyNumber = new Phonenumber(1L);
        dummyNumber.setValue(dummyPersonDto.getPhonenumbers().get(0).getValue());
        Mockito.when(phonenumberRepo.findByValue(dummyPersonDto.getId(), dummyPersonDto.getPhonenumbers().get(0).getValue()))
                .thenReturn(dummyNumber);
        verifier.correlateNumbers(dummyPersonDto);
        Assert.assertEquals(dummyNumber.getId(), dummyPersonDto.getPhonenumbers().get(0).getId());
    }
    
    @Test
    public void testPhonenumberExistsId() {
    	dummyPersonDto.getPhonenumbers().get(0).setId(1L);
        Phonenumber dummyNumber = new Phonenumber(1L);
        dummyNumber.setValue(dummyPersonDto.getPhonenumbers().get(0).getValue());
        Mockito.when(phonenumberRepo.find(1L))
                .thenReturn(dummyNumber);
        verifier.correlateNumbers(dummyPersonDto);
        Assert.assertEquals(dummyNumber.getId(), dummyPersonDto.getPhonenumbers().get(0).getId());
    }

    @Test
    public void testPhonenumber() {
        verifier.correlateNumbers(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testMailaddressWrong() {
        dummyPersonDto.getAdditionalMails().get(0).setId(1L);
        verifier.correlateMails(dummyPersonDto);
    }

    @Test
    public void testMailaddressExists() {
        Mailaddress dummymail = new Mailaddress(1L);
        dummymail.setValue(dummyPersonDto.getAdditionalMails().get(0).getValue());
        Mockito.when(mailRepo.findByValue(dummyPersonDto.getId(), dummyPersonDto.getAdditionalMails().get(0).getValue()))
                .thenReturn(dummymail);
        verifier.correlateMails(dummyPersonDto);
        Assert.assertEquals(dummymail.getId(), dummyPersonDto.getAdditionalMails().get(0).getId());
    }

    @Test
    public void testMailaddressExistsId() {
    	dummyPersonDto.getAdditionalMails().get(0).setId(1L);
        Mailaddress dummymail = new Mailaddress(1L);
        dummymail.setValue(dummyPersonDto.getAdditionalMails().get(0).getValue());
        Mockito.when(mailRepo.find(1L))
                .thenReturn(dummymail);
        verifier.correlateMails(dummyPersonDto);
        Assert.assertEquals(dummymail.getId(), dummyPersonDto.getAdditionalMails().get(0).getId());
    }
    
    @Test
    public void testMailaddress() {
        verifier.correlateMails(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testHobbyWrong() {
        dummyPersonDto.getHobbies().get(0).setId(1L);
        verifier.correlateHobbies(dummyPersonDto);
    }

    @Test
    public void testHobbyExists() {
        Hobby dummyhobby = new Hobby(1L);
        dummyhobby.setValue(dummyPersonDto.getHobbies().get(0).getValue());
        Mockito.when(hobbyRepo.findByValue(dummyPersonDto.getId(), dummyPersonDto.getHobbies().get(0).getValue()))
                .thenReturn(dummyhobby);
        verifier.correlateHobbies(dummyPersonDto);
        Assert.assertEquals(dummyhobby.getId(), dummyPersonDto.getHobbies().get(0).getId());
    }
    
    @Test
    public void testHobbyExistsId() {
    	dummyPersonDto.getHobbies().get(0).setId(1L);
        Hobby dummyhobby = new Hobby(1L);
        dummyhobby.setValue(dummyPersonDto.getHobbies().get(0).getValue());
        Mockito.when(hobbyRepo.find(1L))
                .thenReturn(dummyhobby);
        verifier.correlateHobbies(dummyPersonDto);
        Assert.assertEquals(dummyhobby.getId(), dummyPersonDto.getHobbies().get(0).getId());
    }

    @Test
    public void testHobby() {
        verifier.correlateHobbies(dummyPersonDto);
    }

    @Test(expected = VerificationException.class)
    public void testKnowledgeWrong() {
        dummyPersonDto.getKnowledges().get(0).setId(1L);
        verifier.correlateKnowledges(dummyPersonDto);
    }

    @Test
    public void testKnowledgeExists() {
        Knowledge dummyKnowledge = new Knowledge(1L);
        dummyKnowledge.setValue(dummyPersonDto.getKnowledges().get(0).getValue());
        Mockito.when(knowledgeRepo.findByValue(dummyPersonDto.getId(), dummyPersonDto.getKnowledges().get(0).getValue()))
                .thenReturn(dummyKnowledge);
        verifier.correlateKnowledges(dummyPersonDto);
        Assert.assertEquals(dummyKnowledge.getId(), dummyPersonDto.getKnowledges().get(0).getId());
    }
    
    @Test
    public void testKnowledgeExistsId() {
    	dummyPersonDto.getKnowledges().get(0).setId(1L);
        Knowledge dummyKnowledge = new Knowledge(1L);
        dummyKnowledge.setValue(dummyPersonDto.getKnowledges().get(0).getValue());
        Mockito.when(knowledgeRepo.find(1L))
                .thenReturn(dummyKnowledge);
        verifier.correlateKnowledges(dummyPersonDto);
        Assert.assertEquals(dummyKnowledge.getId(), dummyPersonDto.getKnowledges().get(0).getId());
    }

    @Test
    public void testKnowledge() {
        verifier.correlateKnowledges(dummyPersonDto);
    }


}
