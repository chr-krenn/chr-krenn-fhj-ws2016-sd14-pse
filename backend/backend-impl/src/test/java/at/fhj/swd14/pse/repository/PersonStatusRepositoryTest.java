package at.fhj.swd14.pse.repository;

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

import at.fhj.swd14.pse.person.Status;

@RunWith(MockitoJUnitRunner.class)
public class PersonStatusRepositoryTest {

	@InjectMocks
	private PersonStatusRepository repo;
	
	@Mock
	private EntityManager manager;
	
	private TypedQuery<Status> query;
	@SuppressWarnings("unchecked")
	@Before
	public void setup()
	{
		query = Mockito.mock(TypedQuery.class);
		Mockito.when(manager.createNamedQuery(Mockito.anyString(), Mockito.eq(Status.class))).thenReturn(query);
		Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
	}
	
	
	@Test
	public void testResult()
	{
		Status personStatus = new Status();
		Mockito.when(query.getSingleResult()).thenReturn(personStatus);
		Assert.assertEquals(personStatus, repo.findByName("test"));
	}
	
}
