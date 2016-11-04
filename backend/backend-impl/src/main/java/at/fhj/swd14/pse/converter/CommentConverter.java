package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentDto;

public class CommentConverter {
		
	public CommentConverter() {

	}

	public static CommentDto convert(Comment comment) {
		if (comment == null) {
			return null;
		}

		CommentDto dto = new CommentDto(comment.getId());
		dto.setAuthor(UserConverter.convert(comment.getAuthor()));
		dto.setParentMessage(MessageConverter.convert(comment.getParentMessage()));
		dto.setText(comment.getText());
		return dto;
	}

	public static Comment convert(CommentDto dto) {
		if (dto == null) {
			return null;
		}
		Comment comment = new Comment(dto.getId());
		comment.setAuthor(UserConverter.convert(dto.getAuthor()));
		comment.setParentMessage(MessageConverter.convert(dto.getParentMessage()));
		comment.setText(dto.getText());
		return comment;
	}

	public static Collection<CommentDto> convertToDtoList(Collection<Comment> comments) {
		if (comments == null) {
			return null;
		}
		return comments.stream().map(CommentConverter::convert).collect(Collectors.toList());
	}

	public static Collection<Comment> convertToDoList(Collection<CommentDto> comments) {
		if (comments == null) {
			return null;
		}
		return comments.stream().map(CommentConverter::convert).collect(Collectors.toList());
	}
}
