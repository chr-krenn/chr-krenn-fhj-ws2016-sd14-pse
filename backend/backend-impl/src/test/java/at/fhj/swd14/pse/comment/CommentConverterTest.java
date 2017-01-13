package at.fhj.swd14.pse.comment;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

public class CommentConverterTest
{
	private Comment comment;
	private CommentDto commentDto;

	@Before
	public void setup()
	{
		comment = new Comment(1L);
		comment.setText("blabla");
		comment.setParentMessage(new Message(1L));
		comment.setAuthor(new User(1L));

		commentDto = new CommentDto(1L);
		commentDto.setText("blabla");
		commentDto.setParentMessage(new MessageDto(1L));
		commentDto.setAuthor(new UserDto(1L));
	}

	@Test
	public void testConvertToComment()
	{
		Comment commentConverter = CommentConverter.convert(commentDto);

		Assert.assertEquals(comment.getId(), commentConverter.getId());
		Assert.assertEquals(comment.getText(), commentConverter.getText());
	}

	@Test
	public void testConvertToCommentNull()
	{
		CommentDto commentDtoNull = null;

		Comment commentConverter = CommentConverter.convert(commentDtoNull, null);

		Assert.assertNull(commentConverter);
	}

	@Test
	public void testConvertToCommentMessageNull()
	{
		commentDto.setParentMessage(null);

		Comment commentConverter = CommentConverter.convert(commentDto);

		Assert.assertNull(commentConverter.getParentMessage());
	}

	@Test
	public void testConvertToDto()
	{
		CommentDto commentConverter = CommentConverter.convert(comment);

		Assert.assertEquals(commentDto.getId(), commentConverter.getId());
		Assert.assertEquals(commentDto.getText(), commentConverter.getText());
		Assert.assertEquals(commentDto.getAuthor().getId(), commentConverter.getAuthor().getId());
	}

	@Test
	public void testConvertToDtoNull()
	{
		comment.setParentMessage(null);

		CommentDto commentConverter = CommentConverter.convert(comment);

		Assert.assertEquals(commentDto.getId(), commentConverter.getId());
		Assert.assertEquals(commentDto.getText(), commentConverter.getText());
		Assert.assertEquals(commentDto.getAuthor().getId(), commentConverter.getAuthor().getId());
	}

	@Test
	public void testConvertToDtoMessage()
	{
		commentDto.setParentMessage(new MessageDto(1L));

		MessageDto parentMessage = new MessageDto(1L);

		CommentDto commentConverter = CommentConverter.convert(comment, parentMessage);

		Assert.assertEquals(commentDto.getParentMessage(), commentConverter.getParentMessage());
	}

	@Test
	public void testConvertToDtoMessageNull()
	{
		comment = null;

		MessageDto parentMessage = new MessageDto(1L);

		CommentDto commentConverter = CommentConverter.convert(comment, parentMessage);

		Assert.assertNull(commentConverter);
	}

	@Test
	public void testConvertToDoListComment()
	{
		Collection<CommentDto> dtoComments = new LinkedList<>();
		dtoComments.add(new CommentDto(1L));
		dtoComments.add(new CommentDto(2L));

		Collection<Comment> comments = CommentConverter.convertToDoList(dtoComments);

		Assert.assertEquals(dtoComments.size(), comments.size());
	}

	@Test
	public void testConvertToDoListCommentNull()
	{
		Collection<CommentDto> dtoComments = null;

		Collection<Comment> comments = CommentConverter.convertToDoList(dtoComments);

		Assert.assertNull(comments);
	}

	@Test
	public void testConvertToDoListDto()
	{
		Collection<Comment> comments = new LinkedList<>();
		comments.add(new Comment(1L));
		comments.add(new Comment(2L));

		Collection<CommentDto> dtoComments = CommentConverter.convertToDtoList(comments);

		Assert.assertEquals(comments.size(), dtoComments.size());
	}

	@Test
	public void testConvertToDoListDtoNull()
	{
		Collection<Comment> comments = null;

		Collection<CommentDto> dtoComments = CommentConverter.convertToDtoList(comments);

		Assert.assertNull(dtoComments);
	}
}
