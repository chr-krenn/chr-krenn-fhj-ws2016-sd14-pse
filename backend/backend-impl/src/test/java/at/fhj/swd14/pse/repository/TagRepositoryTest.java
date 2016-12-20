package at.fhj.swd14.pse.repository;


import at.fhj.swd14.pse.tag.Tag;
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
public class TagRepositoryTest {

    @InjectMocks
    private TagRepository repo;

    @Mock
    private EntityManager manager;

    private List<Tag> resultList;

    @SuppressWarnings("unchecked")
    @Before
    public void setup()
    {
        TypedQuery<Tag> query = Mockito.mock(TypedQuery.class);
        Mockito.when(manager.createNamedQuery(Mockito.anyString(), Mockito.eq(Tag.class))).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
        resultList = new LinkedList<>();
        Mockito.when(query.getResultList()).thenReturn(resultList);
    }

    @Test
    public void testNoResult()
    {
        Assert.assertNull(repo.findByName("gibtsned"));
    }

    @Test
    public void testResult()
    {
        Tag tag = new Tag(1L, "gibts");
        resultList.add(tag);
        Assert.assertEquals(tag, repo.findByName("gibts"));
    }
}
