package at.fhj.swd14.pse.message;


import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.tag.TagDto;
import at.fhj.swd14.pse.tag.TagServiceException;
import at.fhj.swd14.pse.tag.TagServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MessageTagHandlerTest {
	@InjectMocks
	private MessageTagHandler msgTagHandler;

	@Mock
	private TagServiceImpl tagService;
	
	private Message message;
	private List<String> tags;
	
	@Before
    public void setup() {
    	message = new Message(1L);
    	
    	tags = new ArrayList<String>();

    }
	
	public void assertEquals(List<String> expected, List<String> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		
		for (int i = 0; i < expected.size(); i++) {
			Assert.assertTrue(findInList(expected.get(i), actual));
		}
	}
	
	public Boolean findInList(String search, List<String> items) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(search))
				return true;
		}
		return false;
	}
	
	public void testOneTagInTitle(String title, String tag) {
    	message.setTitle(title);
    	Mockito.when(tagService.save(Mockito.any())).thenReturn(0L);
    	MessageDto m = MessageConverter.convert(message);
    	msgTagHandler.handleTags(m);

    	
    	Assert.assertEquals(1, m.getTags().size());
    	Assert.assertEquals(tag, m.getTags().get(0).getName());
	}
	
    @Test
    public void testTagInTitle() {
    	testOneTagInTitle("test #testtag text", "testtag");
    	testOneTagInTitle("#begin test text", "begin");
    	testOneTagInTitle("test text #end", "end");
    }

	public void testOneTagInContent(String content, String tag) {
    	message.setContent(content);
    	
    	Mockito.when(tagService.save(Mockito.any())).thenReturn(0L);
    	MessageDto m = MessageConverter.convert(message);
    	msgTagHandler.handleTags(m);
    	
    	Assert.assertEquals(1, m.getTags().size());
    	Assert.assertEquals(tag, m.getTags().get(0).getName());
	}

	@Test
    public void testTagInContent() {
		testOneTagInContent("test #testtag text", "testtag");
    	testOneTagInContent("#begin test text", "begin");
    	testOneTagInContent("test text #end", "end");
    }
	
	public void testTagsInMesage(String title, String content, List <String>tags) {
    	message.setTitle(title);
    	message.setContent(content);

    	Mockito.when(tagService.save(Mockito.any())).thenReturn(0L);
    	MessageDto m = MessageConverter.convert(message);
    	msgTagHandler.handleTags(m);
    
    	List<String> msgTags = new ArrayList<>();
    	
    	if (m.getTags() != null) {
	    	for (TagDto t: m.getTags()) {
	    	    msgTags.add(t.getName());
	    	}
    	}	
    	assertEquals(tags, msgTags);
	}

	@Test
    public void testMultipleTagsInMessage() {
		tags.add("titletag");
		tags.add("contenttag");
		tags.add("onemoretag");
		
		testTagsInMesage("title #titletag in my text", "my content with #contenttag and #onemoretag", tags);
    }

	@Test
    public void testRandomHashSymbolInMessage() {
		tags.add("titletag");
		tags.add("titletag2");
		tags.add("contenttag");
		tags.add("onemoretag");
		
		testTagsInMesage("# title #titletag in #titletag2 my # text", "my content with #contenttag and #onemoretag", tags);
    }

	@Test
    public void testHastagsWithSpecialCharacterInMessage() {
		tags.add("title_tag");
		tags.add("one");
		
		testTagsInMesage("title #title_tag in #/titletag2 my # text", "my content with #%contenttag and #one%moretag", tags);
    }
	
	@Test
    public void testNoHashtagsInMessage() {
		
		testTagsInMesage("only text here", "only text here", tags);
    }
	
	
	@Test(expected=MessageTagHandlerException.class)
    public void testExceptionMessageNull() {
		msgTagHandler.handleTags(null);	
		
    }

	@Test(expected=MessageTagHandlerException.class)
    public void testExceptionTagServiceVerification() {
		message.setContent("text #tag");
		Mockito.doThrow(new VerificationException("TagDto was null")).when(tagService).save(Matchers.any(TagDto.class));  
		
    	msgTagHandler.handleTags(MessageConverter.convert(message));
		
    }

	@Test(expected=MessageTagHandlerException.class)
    public void testExceptionTagServiceItself() {
		message.setContent("text #tag");
		Mockito.doThrow(new TagServiceException("timeout")).when(tagService).save(Matchers.any(TagDto.class));  
		
    	msgTagHandler.handleTags(MessageConverter.convert(message));
		
    }
	
	@Test(expected=MessageTagHandlerException.class)
    public void testExceptionTagIsNull() {
	
    	msgTagHandler.handleTags(null);
		
    }

	@Test
    public void testServiceStubCoverageAssert() {
	
		assertNull(tagService.find(1L));
		assertNull(tagService.findByName("null"));
		
    }
	
}
