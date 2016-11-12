package at.fhj.swd14.pse.converter;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

public class MessageConverterTest {

	private static User author;
	private static UserDto authorDto;
	private static User recipient;
	private static UserDto recipientDto;
	
	// TODO test with community class
	private static Long communityId;
	
	@BeforeClass
	public static void setup(){
		
		author = new User(100L);
		author.setMail("author@swd14.com");
		authorDto = new UserDto(100L);
		authorDto.setMail("author@swd14.com");
		
		recipient = new User(200L);
		recipient.setMail("recipient@swd14.com");
		recipientDto = new UserDto(200L);
		recipient.setMail("recipient@swd14.com");
		
		communityId = 1L;
	}
	
	@Test
	public void testConvertToDto(){
		Message m = createEntity(1000L, "It's a title", "It's a content");
		MessageDto dto = MessageConverter.convert(m);
		assertConverted(m, dto);
	}
	
	@Test
	public void testConvertToEntity(){
		MessageDto dto = createDto(1000L, "It's a title", "It's a content");
		Message m = MessageConverter.convert(dto);
		assertConverted(dto, m);
	}
	
	@Test
	public void testConvertListToDto(){
		Collection<Message> messages = new LinkedList<>();
		messages.add(createEntity(1000L, "first title", "first content"));
		messages.add(createEntity(2000L, "second title", "second content"));
		
		Collection<MessageDto> dtos = MessageConverter.convertToDtoList(messages);
		assertEquals(messages.size(), dtos.size());
		
		for(int i=0;i<messages.size();i++){
			assertConverted((Message)messages.toArray()[i], (MessageDto)dtos.toArray()[i]);
		}
	}
	
	@Test
	public void testConvertListToEntity(){
		Collection<MessageDto> dtos = new LinkedList<>();
		dtos.add(createDto(1000L, "first title", "first content"));
		dtos.add(createDto(2000L, "second title", "second content"));
		
		Collection<Message> messages = MessageConverter.convertToDoList(dtos);
		assertEquals(dtos.size(), messages.size());
		
		for(int i=0;i<dtos.size();i++){
			assertConverted((MessageDto)dtos.toArray()[i], (Message)messages.toArray()[i]);
		}
	}
	
	private void assertConverted(Message expected, MessageDto actual){
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
		assertEquals(expected.getRecipient().getId(), actual.getRecipient().getId());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getContent(), actual.getContent());
		assertEquals(expected.getChilds().size(), actual.getChilds().size());
		
		for(int i=0;i<expected.getChilds().size();i++){
			assertEquals(expected.getChilds().get(i).getId(), actual.getChilds().get(i).getId());
		}
	}
	
	private void assertConverted(MessageDto expected, Message actual){
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
		assertEquals(expected.getRecipient().getId(), actual.getRecipient().getId());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getContent(), actual.getContent());
		assertEquals(expected.getChilds().size(), actual.getChilds().size());
		
		for(int i=0;i<expected.getChilds().size();i++){
			assertEquals(expected.getChilds().get(i).getId(), actual.getChilds().get(i).getId());
		}
	}
	
	private Message createEntity(Long id, String title, String content)
	{
		Message m = new Message(id);
		m.setAuthor(author);
		m.setRecipient(recipient);
		m.setCommunityId(communityId);
		m.setTitle(title);
		m.setContent(content);
		m.setChilds(createCommentEntities());
		return m;
	}
	
	private MessageDto createDto(Long id, String title, String content){
		MessageDto dto = new MessageDto(id);
		dto.setAuthor(authorDto);
		dto.setRecipient(recipientDto);
		dto.setCommunityId(communityId);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setChilds(createCommentDtos());
		return dto;
	}
	
	private List<Comment> createCommentEntities(){
		List<Comment> comments = new LinkedList<>();
		comments.add(new Comment(1L));
		comments.add(new Comment(2L));
		return comments;
	}
	
	private List<CommentDto> createCommentDtos(){
		
		List<CommentDto> comments = new LinkedList<>();
		comments.add(new CommentDto(1L));
		comments.add(new CommentDto(2L));
		return comments;
	}
}