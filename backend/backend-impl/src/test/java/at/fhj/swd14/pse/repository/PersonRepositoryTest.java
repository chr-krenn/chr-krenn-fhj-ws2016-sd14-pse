package at.fhj.swd14.pse.repository;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.person.Person;

@RunWith(MockitoJUnitRunner.class)
public class PersonRepositoryTest {

	@InjectMocks
	private PersonRepository repo;
	
	@Mock
	private EntityManager manager;
	
	private List<Person> resultList;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup()
	{
		TypedQuery<Person> query = Mockito.mock(TypedQuery.class);
		Mockito.when(manager.createNamedQuery(Mockito.anyString(), Mockito.eq(Person.class))).thenReturn(query);
		Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
		resultList = new LinkedList<>();
		Mockito.when(query.getResultList()).thenReturn(resultList);
	}
	
	@Test
	public void testNoResult()
	{
		Assert.assertNull(repo.findByUserId(1L));
	}
	
	@Test
	public void testResult()
	{
		Person person = new Person();
		resultList.add(person);
		Assert.assertEquals(person, repo.findByUserId(1L));
	}
	
}
