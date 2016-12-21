package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.PersonImage;
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
public class PersonImageRepositoryImplTest {

    @InjectMocks
    private PersonImageRepositoryImpl repo;

    @Mock
    private EntityManager manager;

    private List<PersonImage> resultList;

    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        TypedQuery<PersonImage> query = Mockito.mock(TypedQuery.class);
        Mockito.when(manager.createNamedQuery(Mockito.anyString(), Mockito.eq(PersonImage.class))).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
        resultList = new LinkedList<>();
        Mockito.when(query.getResultList()).thenReturn(resultList);
    }

    @Test
    public void testNoResult() {
        Assert.assertNull(repo.getByPersonId(1L));
    }

    @Test
    public void testResult() {
        PersonImage image = new PersonImage();
        resultList.add(image);
        Assert.assertEquals(image, repo.getByPersonId(1L));
    }

}
