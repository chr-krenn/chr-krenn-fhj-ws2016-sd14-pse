package at.fhj.swd14.pse.person.tools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import at.fhj.swd14.pse.person.PersonUtil;
import at.fhj.swd14.pse.person.HobbyDto;
import at.fhj.swd14.pse.person.KnowledgeDto;
import at.fhj.swd14.pse.person.MailaddressDto;
import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PhonenumberDto;

public class PersonVerifierTest {
	
	private PersonVerifier verifier;
	private PersonBean bean;
	private PersonDto person;
	
	@Before
	public void setup()
	{
		bean = Mockito.mock(PersonBean.class);
		verifier = new PersonVerifier(bean);
		person= PersonUtil.getDummyPerson();
		Mockito.when(bean.getPerson()).thenReturn(person);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBeanNull()
	{
		new PersonVerifier(null);
	}
	
	private void verifyNameFailure()
	{
		boolean result = verifier.verifyName();
		Assert.assertFalse(result);
		Mockito.verify(bean,Mockito.times(1)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testFirstnameNull()
	{
		person.setFirstname(null);
		verifyNameFailure();
	}
	
	@Test
	public void testFirstnameEmpty()
	{
		person.setFirstname("");
		verifyNameFailure();
	}
	
	@Test
	public void testLastnameNull()
	{
		person.setLastname(null);
		verifyNameFailure();
	}
	
	@Test
	public void testLastnameEmpty()
	{
		person.setLastname("");
		verifyNameFailure();
	}
	
	@Test
	public void testNameOk()
	{
		boolean result = verifier.verifyName();
		Assert.assertTrue(result);
		Mockito.verify(bean,Mockito.times(0)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	private void verifyMailFailure(MailaddressDto mail)
	{
		boolean result = verifier.verifyMail(mail);
		Assert.assertFalse(result);
		Mockito.verify(bean,Mockito.times(1)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testMailNull()
	{
		verifyMailFailure(null);
	}
	
	@Test
	public void testMailValueNull()
	{
		MailaddressDto mail = new MailaddressDto();
		mail.setValue(null);
		verifyMailFailure(mail);
	}
	
	@Test
	public void testMailValueEmpty()
	{
		MailaddressDto mail = new MailaddressDto();
		mail.setValue("");
		verifyMailFailure(mail);
	}
	
	@Test
	public void testMailInvalid()
	{
		MailaddressDto mail = new MailaddressDto();
		mail.setValue("test");
		verifyMailFailure(mail);
	}
	
	@Test
	public void testMailOk()
	{
		MailaddressDto mail = new MailaddressDto();
		mail.setValue("test@test.de");
		boolean result = verifier.verifyMail(mail);
		Assert.assertTrue(result);
		Mockito.verify(bean,Mockito.times(0)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	private void verifyHobbyFailure(HobbyDto hobby)
	{
		boolean result = verifier.verifyHobby(hobby);
		Assert.assertFalse(result);
		Mockito.verify(bean,Mockito.times(1)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test 
	public void testHobbyNull()
	{
		verifyHobbyFailure(null);
	}
	
	@Test
	public void testHobbyValueNull()
	{
		HobbyDto hobby = new HobbyDto();
		hobby.setValue(null);
		verifyHobbyFailure(hobby);
	}
	
	@Test
	public void testHobbyValueEmpty()
	{
		HobbyDto hobby = new HobbyDto();
		hobby.setValue("");
		verifyHobbyFailure(hobby);
	}
	
	@Test 
	public void testHobbyOK()
	{
		HobbyDto hobby = new HobbyDto();
		hobby.setValue("ok");
		boolean result = verifier.verifyHobby(hobby);
		Assert.assertTrue(result);
		Mockito.verify(bean,Mockito.times(0)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	private void verifyKnowledgeFailure(KnowledgeDto knowledge)
	{
		boolean result = verifier.verifyKnowledge(knowledge);
		Assert.assertFalse(result);
		Mockito.verify(bean,Mockito.times(1)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test 
	public void testKnowledgeNull()
	{
		verifyKnowledgeFailure(null);
	}
	
	@Test
	public void testKnowledgeValueNull()
	{
		KnowledgeDto knowledge = new KnowledgeDto();
		knowledge.setValue(null);
		verifyKnowledgeFailure(knowledge);
	}
	
	@Test
	public void testKnowledgeValueEmpty()
	{
		KnowledgeDto knowledge = new KnowledgeDto();
		knowledge.setValue("");
		verifyKnowledgeFailure(knowledge);
	}
	
	@Test 
	public void testKnowledgeOK()
	{
		KnowledgeDto knowledge = new KnowledgeDto();
		knowledge.setValue("ok");
		boolean result = verifier.verifyKnowledge(knowledge);
		Assert.assertTrue(result);
		Mockito.verify(bean,Mockito.times(0)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	private void verifyNumberFailure(PhonenumberDto number)
	{
		boolean result = verifier.verifyNumber(number);
		Assert.assertFalse(result);
		Mockito.verify(bean,Mockito.times(1)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testNumberNull()
	{
		verifyNumberFailure(null);
	}
	
	@Test
	public void testNumberValueNull()
	{
		PhonenumberDto number = new PhonenumberDto();
		number.setValue(null);
		verifyNumberFailure(number);
	}
	
	@Test
	public void testNumberValueEmpty()
	{
		PhonenumberDto number = new PhonenumberDto();
		number.setValue("");
		verifyNumberFailure(number);
	}
	
	@Test
	public void testNumberInvalid()
	{
		PhonenumberDto number = new PhonenumberDto();
		number.setValue("ab");
		verifyNumberFailure(number);
	}
	
	@Test
	public void testNumberOk()
	{
		PhonenumberDto number = new PhonenumberDto();
		number.setValue("+43660660660");
		boolean result = verifier.verifyNumber(number);
		Assert.assertTrue(result);
		Mockito.verify(bean,Mockito.times(0)).growl(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testNameWrong()
	{
		person.setFirstname(null);
		Assert.assertFalse(verifier.verifyPerson());
	}
	
	@Test
	public void testMailWrong()
	{
		person.getAdditionalMails().get(0).setValue(null);
		Assert.assertFalse(verifier.verifyPerson());
	}
	
	@Test
	public void testHobbyWrong()
	{
		person.getHobbies().get(0).setValue(null);
		Assert.assertFalse(verifier.verifyPerson());
	}
	
	@Test
	public void testKnowledgeWrong()
	{
		person.getKnowledges().get(0).setValue(null);
		Assert.assertFalse(verifier.verifyPerson());
	}
	
	@Test
	public void testNumberWrong()
	{
		person.getPhonenumbers().get(0).setValue(null);
		Assert.assertFalse(verifier.verifyPerson());
	}
	
	@Test
	public void testOk()
	{
		Assert.assertTrue(verifier.verifyPerson());
	}

}
