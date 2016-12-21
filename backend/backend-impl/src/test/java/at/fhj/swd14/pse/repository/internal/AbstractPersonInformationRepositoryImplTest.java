package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Hobby;
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
import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AbstractPersonInformationRepositoryImplTest {

    @InjectMocks
    private HobbyRepositoryImpl repo;

    @Mock
    private EntityManager manager;

    private List<Hobby> resultList;

    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        TypedQuery<Hobby> query = Mockito.mock(TypedQuery.class);
        Mockito.when(manager.createNamedQuery(Mockito.anyString(), Mockito.eq(Hobby.class))).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
        resultList = new LinkedList<>();
        Mockito.when(query.getResultList()).thenReturn(resultList);
    }

    @Test
    public void testNoResult() {
        Assert.assertNull(repo.findByValue(1L, "test"));
    }

    @Test
    public void testResult() {
        Hobby hobby = new Hobby();
        resultList.add(hobby);
        Assert.assertEquals(hobby, repo.findByValue(1L, "test"));
    }

}
