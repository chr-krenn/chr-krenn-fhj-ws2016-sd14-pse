package at.fhj.swd14.pse.tag;

import java.util.ArrayList;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import at.fhj.swd14.pse.BaseIntegrationTest;
import at.fhj.swd14.pse.base.IntegrationTestUtil;
import at.fhj.swd14.pse.message.MessageDto;
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
	@Ignore
	//TODO TagService -> Wenn ein Tag gespeichert wird, dann auch in die Kreuztabelle mitspeichern, bzw sollte das bei der Message mit passieren, dann bitte den Test anpassen
	public void testSaveAndFindByIdTag(){
		TagDto tag = new TagDto();
		ArrayList<MessageDto> messages = new ArrayList<MessageDto>();
		messages.add(message);
		tag.setMessages(messages);
		tag.setName("test_tag");
		long tagId = tagservice.save(tag);
		tag.setId(tagId);
		
		TagDto found = tagservice.find(tagId);
		Assert.assertEquals(tag.getId(), found.getId());
		Assert.assertEquals(tag.getName(), found.getName());
		Assert.assertEquals(tag.getMessages().size(), found.getMessages().size());
	}
	
	@Test
	@Ignore
	//TODO TagService -> Wenn ein Tag gespeichert wird, dann auch in die Kreuztabelle mitspeichern, bzw sollte das bei der Message mit passieren, dann bitte den Test anpassen
	//TODO TagService -> Wenn ein Tag gespeichert wird mit dem selben Namen, dann nicht einfach speichern sondern die Id vom vorhandenen nehmen ansonsten kommt bei findByTagName immer nur der Erste Eintrag zurück -> schirch!!!
	public void testSaveAndFindByNameTag(){
		TagDto tag = new TagDto();
		ArrayList<MessageDto> messages = new ArrayList<MessageDto>();
		messages.add(message);
		tag.setMessages(messages);
		tag.setName("test_tag");
		long tagId = tagservice.save(tag);
		tag.setId(tagId);
		
		TagDto found = tagservice.findByName("test_tag");
		Assert.assertEquals(tag.getId(), found.getId());
		Assert.assertEquals(tag.getName(), found.getName());
		Assert.assertEquals(tag.getMessages().size(), found.getMessages().size());
	}
}
