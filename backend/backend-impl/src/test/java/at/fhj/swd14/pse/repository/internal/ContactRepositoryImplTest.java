package at.fhj.swd14.pse.repository.internal;

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

import at.fhj.swd14.pse.contact.Contact;

@RunWith(MockitoJUnitRunner.class)
public class ContactRepositoryImplTest {

	@InjectMocks
    private ContactRepositoryImpl repo;

    @Mock
    private EntityManager manager;
    
    private List<Contact> resultList;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        TypedQuery<Contact> query = Mockito.mock(TypedQuery.class);
        Mockito.when(manager.createNamedQuery(Mockito.anyString(), Mockito.eq(Contact.class))).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
        resultList = new LinkedList<>();
        Mockito.when(query.getResultList()).thenReturn(resultList);
    }
    
    @Test
    public void testResult() {
        Contact contact = new Contact(1L, 2L);
        resultList.add(contact);
        Assert.assertEquals(contact, repo.findByPersonId(1L).get(0));
    }
}
