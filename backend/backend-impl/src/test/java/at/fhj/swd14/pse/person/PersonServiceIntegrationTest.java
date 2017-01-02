package at.fhj.swd14.pse.person;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import at.fhj.swd14.pse.base.IntegrationTestUtil;
import at.fhj.swd14.pse.database.DatabaseTestUtil;
import at.fhj.swd14.pse.user.UserConverter;

public class PersonServiceIntegrationTest {

	private PersonService service;
	private EntityManager manager;
	
	@Before
	public void setup()
	{
		service = IntegrationTestUtil.getService(PersonService.class);
		manager = DatabaseTestUtil.getEntityManager();
	}
	
	private Person getAnyPerson()
	{
		List<Person> persons = manager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
		Assert.assertTrue(persons.size()>0);
		return persons.get(0);
	}
	
	private void validatePerson(Person expected, PersonDto actual)
	{
		Assert.assertNotNull(actual);
		PersonDtoTester.assertEquals(PersonConverter.convert(expected), actual);
	}
	
	@Test
	@Ignore
	public void testFind()
	{
		Person person = getAnyPerson();
		
		PersonDto foundPerson = service.find(person.getId());
		
		validatePerson(person,foundPerson);
		
	}
	
	@Test
	@Ignore
	public void testFindByUser()
	{
		Person person = getAnyPerson();
		
		PersonDto foundPerson = service.findByUser(UserConverter.convert(person.getUser()));
		
		validatePerson(person,foundPerson);
	}
	
	@Test
	@Ignore
	public void testSaveLoggedInPerson()
	{
		Person person = getAnyPerson();
		//just modify a field in the person
		String oldAddress = person.getAddress();
		String expectedAddress = person.getAddress()!=null?person.getAddress()+"Test":"Test";
		person.setAddress(expectedAddress);
		service.saveLoggedInPerson(PersonConverter.convert(person));
		
		EntityTransaction trans = manager.getTransaction();
		trans.begin();
		manager.refresh(person);
		String actualAddress = person.getAddress();
		person.setAddress(oldAddress);
		manager.merge(person);
		manager.flush();
		trans.commit();
		
		Assert.assertEquals(expectedAddress, actualAddress);
		
	}
	
	
	@Test
	@Ignore
	public void testFindAllStati()
	{
		final String[] stati = new String[]{"online","offline","abwesend"};
		
		Collection<StatusDto> svcStati = service.findAllStati();
		
		Assert.assertNotNull(svcStati);
		Assert.assertEquals(3, svcStati.size());
		
		int found = 0;
		
		for(StatusDto status : svcStati)
		{
			for(int i = 0;i<stati.length;i++)
			{
				if(status.getName().equals(stati[i]))
				{
					found|=(int)Math.pow(2, i);
				}
			}
		}
		
		Assert.assertEquals((int)Math.pow(2, stati.length)-1, found);
	}
	
	@Test
	@Ignore
	public void testPersonImage()
	{
		Person person = getAnyPerson();
		
		//save any existing images
		List<PersonImage> existingImgs = manager.createQuery("SELECT i FROM PersonImage i "
				+ "											  WHERE i.person.id=:personid",PersonImage.class)
				.setParameter("personid", person.getId())
				.getResultList();
		
		byte[] data = new byte[]{1,2,3,4};
		
		service.savePersonImage(PersonConverter.convert(person), data,"image/jpeg");
		
		List<PersonImage> newImgs=null;
		newImgs = manager.createQuery("SELECT i FROM PersonImage i "
				+ "					   WHERE i.person.id=:personid",PersonImage.class)
				.setParameter("personid", person.getId())
				.getResultList();
		
		byte[] newData = newImgs.get(0).getData();
		String newType = newImgs.get(0).getContentType();
		
		EntityTransaction trans = manager.getTransaction();
		trans.begin();
		//restore the state
		if(newImgs.size()>0)
			manager.remove(newImgs.get(0));
		if(existingImgs.size()>0)
			manager.persist(existingImgs.get(0));
		manager.flush();
		trans.commit();
		
		Assert.assertEquals(1, newImgs.size());
		Assert.assertArrayEquals(data, newData);
		Assert.assertEquals("image/jpeg", newType);
	}
	
	@Test
	@Ignore
	public void testGetPersonImage()
	{
		Person person = getAnyPerson();
		
		//check if an image already exists, if not create one
		List<PersonImage> existingImgs = manager.createQuery("SELECT i FROM PersonImage i "
				+ "											  WHERE i.person.id=:personid",PersonImage.class)
				.setParameter("personid", person.getId())
				.getResultList();
		
		EntityTransaction trans = manager.getTransaction();
		PersonImage img = null;
		if(existingImgs.size()==0)
		{
			trans.begin();
			img = new PersonImage();
			img.setId(null);
			img.setContentType("image/jpeg");
			img.setData(new byte[]{1,2,3,4});
			img.setPerson(person);
			manager.persist(img);
			manager.flush();
			trans.commit();
		}
		else
		{
			img = existingImgs.get(0);
		}
		
		PersonImageDto dto = service.getPersonImage(person.getId());
		
		//restore original state
		if(existingImgs.size()==0)
		{
			trans.begin();
			manager.remove(img);
			manager.flush();
			trans.commit();
		}
		
		Assert.assertNotNull(dto);
		Assert.assertEquals(img.getContentType(),dto.getContentType());
		PersonDtoTester.assertEquals(PersonConverter.convert(person), dto.getPerson());
		Assert.assertArrayEquals(img.getData(), dto.getData());
		
	}
}
