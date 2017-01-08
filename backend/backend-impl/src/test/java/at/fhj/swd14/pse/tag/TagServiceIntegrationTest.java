package at.fhj.swd14.pse.tag;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd14.pse.BaseIntegrationTest;
import at.fhj.swd14.pse.base.IntegrationTestUtil;
import org.junit.Assert;

public class TagServiceIntegrationTest extends BaseIntegrationTest {
	private TagService tagservice;


	@Before
    public void setup() throws NamingException {
		tagservice = IntegrationTestUtil.getService(TagService.class);
		initServices();
		insertUser();
		getCommunity();
		insertMessage();
    }
	
	@Test
	public void testSaveAndFindByIdTag(){
		TagDto tag = new TagDto();
		tag.setName("test_tag");
		long tagId = tagservice.save(tag);
		tag.setId(tagId);
		
		TagDto found = tagservice.find(tagId);
		Assert.assertEquals(tag.getId(), found.getId());
		Assert.assertEquals(tag.getName(), found.getName());
	}
	
	@Test
	public void testSaveAndFindByNameTag(){
		TagDto tag = new TagDto();
		tag.setName("test_tag");
		long tagId = tagservice.save(tag);
		tag.setId(tagId);
		
		TagDto found = tagservice.findByName("test_tag");
		Assert.assertEquals(tag.getId(), found.getId());
		Assert.assertEquals(tag.getName(), found.getName());
	}
}
