package at.fhj.swd14.pse.tag;

import at.fhj.swd14.pse.repository.internal.TagRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

/**
 * Created by adelmann
 */

@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplExceptionTest {

    @InjectMocks
    private TagServiceImpl tagServiceImpl;

    @Mock
    private TagRepositoryImpl tagRepository;

    @Test(expected=TagServiceException.class)
    public void testSaveNullTag() {
        tagServiceImpl.save(null);
    }

    @Test(expected=TagServiceException.class)
    public void testSavePersistenceException(){
        Mockito.doThrow(PersistenceException.class).when(tagRepository).save(Mockito.any());
        tagServiceImpl.save(new TagDto(1L, "exception"));
    }

    @Test(expected=TagServiceException.class)
    public void testSaveException(){
        Mockito.doThrow(Exception.class).when(tagRepository).save(Mockito.any());
        tagServiceImpl.save(new TagDto(1L, "exception"));
    }

    @Test(expected = TagServiceException.class)
    public void testFindEntityNotFoundException(){
        Mockito.doThrow(EntityNotFoundException.class).when(tagRepository).find(Mockito.anyLong());
        tagServiceImpl.find(1L);
    }

    @Test(expected = TagServiceException.class)
    public void testFindException(){
        Mockito.doThrow(Exception.class).when(tagRepository).find(Mockito.anyLong());
        tagServiceImpl.find(1L);
    }

    @Test(expected=TagServiceException.class)
    public void testFindByNameNullTag() {
        tagServiceImpl.findByName(null);
    }

    @Test(expected=TagServiceException.class)
    public void testFindByNameEmptyTag() {
        tagServiceImpl.findByName("");
    }

    @Test(expected = TagServiceException.class)
    public void testFindByNameNoResultException(){
        Mockito.doThrow(NoResultException.class).when(tagRepository).executeNamedQuery(Mockito.anyString(), Mockito.anyMap());
        tagServiceImpl.findByName("nonexistent");
    }

    @Test(expected = TagServiceException.class)
    public void testFindByNameQueryTimeoutException(){
        Mockito.doThrow(QueryTimeoutException.class).when(tagRepository).executeNamedQuery(Mockito.anyString(), Mockito.anyMap());
        tagServiceImpl.findByName("nonexistent");
    }

    @Test(expected = TagServiceException.class)
    public void testFindByNameException(){
        Mockito.doThrow(Exception.class).when(tagRepository).executeNamedQuery(Mockito.anyString(), Mockito.anyMap());
        tagServiceImpl.findByName("nonexistent");
    }

    @Test(expected = TagServiceException.class)
    public void testFindAllException(){
        Mockito.doThrow(Exception.class).when(tagRepository).findAll();
        tagServiceImpl.findAll();
    }
}
