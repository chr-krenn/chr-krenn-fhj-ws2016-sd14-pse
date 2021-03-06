package at.fhj.swd14.pse.tag;

import at.fhj.swd14.pse.repository.internal.TagRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {
    @InjectMocks
    private TagServiceImpl service;

    @Mock
    private TagRepositoryImpl tagRepo;

    private List<Tag> tags;

    @Before
    public void setup() throws NamingException {
        tags = new ArrayList<>();
        Tag tag = new Tag(1L);
        tag.setName("testtag1");

        tags.add(tag);

    }

    @Test
    public void testFind() {
        Mockito.when(tagRepo.find(1L)).thenReturn(tags.get(0));
        TagDto t = service.find(1);

        TagAssert.assertEquals(TagConverter.convert(tags.get(0)), t);
    }
    
    @Test
    public void testFindResultEmpty() {
        Mockito.when(tagRepo.find(1L)).thenReturn(tags.get(0));
        TagDto t = service.find(2);

        Assert.assertEquals(null, t);

    }

    @Test
    public void testFindByName() {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("name", "testtag1");

        Mockito.when(tagRepo.executeNamedQuery("Tag.findByName", parameter)).thenReturn(tags);
        TagDto t = service.findByName("testtag1");

        TagAssert.assertEquals(TagConverter.convert(tags.get(0)), t);
    }

    @Test
    public void testFindByNameNotFound(){
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("name", "testtag1");

        Mockito.when(tagRepo.executeNamedQuery("Tag.findByName", parameter)).thenReturn(null);
        TagDto t = service.findByName("gibtsned");

        Assert.assertNull(t);
    }

    @Test
    public void testFindByNameResultEmpty() {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("name", "testtag1");

        Mockito.when(tagRepo.executeNamedQuery("Tag.findByName", parameter)).thenReturn(tags);
        TagDto t = service.findByName("testnull");

        Assert.assertEquals(null, t);

    }
    
    @Test
    public void testFindAll() {

        Mockito.when(tagRepo.findAll()).thenReturn(tags);
        List<TagDto> l = service.findAll();

        Assert.assertEquals(1, l.size());
        TagAssert.assertEquals(TagConverter.convert(tags.get(0)), l.get(0));

    }

    @Test
    public void testSave(){
        Mockito.doNothing().when(tagRepo).save(tags.get(0));
        Long id = service.save(TagConverter.convert(tags.get(0)));
        Assert.assertEquals((Long)1L, id);
    }

    @Test
    public void testSaveDuplicate(){
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("name", "testtag1");

        Mockito.when(tagRepo.executeNamedQuery("Tag.findByName", parameter)).thenReturn(tags);
    	
        Long id = service.save(TagConverter.convert(tags.get(0)));
        Assert.assertEquals((Long)1L, id);
    }
}
