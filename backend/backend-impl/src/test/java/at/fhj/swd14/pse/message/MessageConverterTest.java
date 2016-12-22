package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.community.CommunityDto;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class MessageConverterTest {

    private static User author;
    private static UserDto authorDto;
    private static User recipient;
    private static UserDto recipientDto;
    private static Community community;
    private static CommunityDto communityDto;

    @BeforeClass
    public static void setup() {

        author = new User(100L);
        author.setMail("author@swd14.com");
        authorDto = new UserDto(100L);
        authorDto.setMail("author@swd14.com");

        recipient = new User(200L);
        recipient.setMail("recipient@swd14.com");
        recipientDto = new UserDto(200L);
        recipient.setMail("recipient@swd14.com");

        community = new Community();
        community.setId(300L);
        communityDto = new CommunityDto(300L);
    }

    @Test
    public void testConvertToDto() {
        Message m = createEntity(1000L, "It's a title", "It's a content");
        MessageDto dto = MessageConverter.convert(m);
        assertConverted(m, dto);
    }

    @Test
    public void testConvertNullToDto(){
    	MessageDto dto = MessageConverter.convert((Message)null);
    	assertNull(dto);
    }
    
    @Test
    public void testConvertToEntity() {
        MessageDto dto = createDto(1000L, "It's a title", "It's a content");
        Message m = MessageConverter.convert(dto);
        assertConverted(dto, m);
    }
    
    @Test
    public void testConvertNullToEntity(){
    	Message msg = MessageConverter.convert((MessageDto)null);
    	assertNull(msg);
    }

    @Test
    public void testConvertListToDto() {
        Collection<Message> messages = new LinkedList<>();
        messages.add(createEntity(1000L, "first title", "first content"));
        messages.add(createEntity(2000L, "second title", "second content"));

        Collection<MessageDto> dtos = MessageConverter.convertToDtoList(messages);
        assertEquals(messages.size(), dtos.size());

        for (int i = 0; i < messages.size(); i++) {
            assertConverted((Message) messages.toArray()[i], (MessageDto) dtos.toArray()[i]);
        }
    }
    
    @Test
    public void testConvertNullListToDto(){
    	Collection<MessageDto> dtos = MessageConverter.convertToDtoList((Collection<Message>)null);
    	assertNotNull(dtos);
    	assertEquals(0, dtos.size());
    }

    @Test
    public void testConvertListToEntity() {
        Collection<MessageDto> dtos = new LinkedList<>();
        dtos.add(createDto(1000L, "first title", "first content"));
        dtos.add(createDto(2000L, "second title", "second content"));

        Collection<Message> messages = MessageConverter.convertToDoList(dtos);
        assertEquals(dtos.size(), messages.size());

        for (int i = 0; i < dtos.size(); i++) {
            assertConverted((MessageDto) dtos.toArray()[i], (Message) messages.toArray()[i]);
        }
    }
    
    @Test
    public void testConvertNullListToEntity(){
    	Collection<Message> msgs = MessageConverter.convertToDoList((Collection<MessageDto>)null);
    	assertNotNull(msgs);
    	assertEquals(0, msgs.size());
    }

    private void assertConverted(Message expected, MessageDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
        assertEquals(expected.getRecipient().getId(), actual.getRecipient().getId());
        assertEquals(expected.getCommunity().getId(), actual.getCommunity().getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
        assertEquals(expected.getChilds().size(), actual.getChilds().size());

        for (int i = 0; i < expected.getChilds().size(); i++) {
            assertEquals(expected.getChilds().get(i).getId(), actual.getChilds().get(i).getId());
            assertSame(actual, actual.getChilds().get(i).getParentMessage());
        }
    }

    private void assertConverted(MessageDto expected, Message actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
        assertEquals(expected.getRecipient().getId(), actual.getRecipient().getId());
        assertEquals(expected.getCommunity().getId(), actual.getCommunity().getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
        assertEquals(expected.getChilds().size(), actual.getChilds().size());

        for (int i = 0; i < expected.getChilds().size(); i++) {
            assertEquals(expected.getChilds().get(i).getId(), actual.getChilds().get(i).getId());
            assertSame(actual, actual.getChilds().get(i).getParentMessage());
        }
    }

    private Message createEntity(Long id, String title, String content) {
        Message m = new Message(id);
        m.setAuthor(author);
        m.setRecipient(recipient);
        m.setCommunity(community);
        m.setTitle(title);
        m.setContent(content);
        m.setChilds(createCommentEntities(m));
        return m;
    }

    private MessageDto createDto(Long id, String title, String content) {
        MessageDto dto = new MessageDto(id);
        dto.setAuthor(authorDto);
        dto.setRecipient(recipientDto);
        dto.setCommunity(communityDto);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setChilds(createCommentDtos(dto));
        return dto;
    }

    private List<Comment> createCommentEntities(Message parent) {
        List<Comment> comments = new LinkedList<>();

        Comment comment = new Comment(1L);
        comment.setParentMessage(parent);
        comments.add(comment);

        comment = new Comment(2L);
        comment.setParentMessage(parent);
        comments.add(comment);

        return comments;
    }

    private List<CommentDto> createCommentDtos(MessageDto parent) {
        List<CommentDto> comments = new LinkedList<>();

        CommentDto comment = new CommentDto(1L);
        comment.setParentMessage(parent);
        comments.add(comment);

        comment = new CommentDto(2L);
        comment.setParentMessage(parent);
        comments.add(comment);
        return comments;
    }
}
