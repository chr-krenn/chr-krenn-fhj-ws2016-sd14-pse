package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@RunWith(MockitoJUnitRunner.class)
public class PersonStatusRepositoryImplTest {

    @InjectMocks
    private PersonStatusRepositoryImpl repo;

    @Mock
    private EntityManager manager;

    private TypedQuery<Status> query;

    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        query = Mockito.mock(TypedQuery.class);
        Mockito.when(manager.createNamedQuery(Mockito.anyString(), Mockito.eq(Status.class))).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
    }


    @Test
    public void testResult() {
        Status personStatus = new Status();
        Mockito.when(query.getSingleResult()).thenReturn(personStatus);
        Assert.assertEquals(personStatus, repo.findByName("test"));
    }

}
