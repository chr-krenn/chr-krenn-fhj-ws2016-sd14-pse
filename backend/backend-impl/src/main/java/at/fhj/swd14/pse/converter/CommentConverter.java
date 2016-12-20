package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageDto;

public final class CommentConverter {

    private CommentConverter() {
    }

    public static CommentDto convert(final Comment comment) {
        return convert(comment, comment.getParentMessage() != null ? MessageConverter.convert(comment.getParentMessage()) : null);
    }

    public static CommentDto convert(final Comment comment, final MessageDto parentMessage) {
        if (comment == null) {
            return null;
        }

        final CommentDto dto = new CommentDto(comment.getId());
        dto.setAuthor(UserConverter.convert(comment.getAuthor()));
        dto.setParentMessage(parentMessage);
        dto.setText(comment.getText());
        return dto;
    }

    public static Comment convert(final CommentDto dto) {
        return convert(dto, dto.getParentMessage() != null ? MessageConverter.convert(dto.getParentMessage()) : null);
    }

    public static Comment convert(final CommentDto dto, final Message parentMessage) {
        if (dto == null) {
            return null;
        }
        final Comment comment = new Comment(dto.getId());
        comment.setAuthor(UserConverter.convert(dto.getAuthor()));
        comment.setParentMessage(parentMessage);
        comment.setText(dto.getText());
        return comment;
    }

    public static Collection<CommentDto> convertToDtoList(final Collection<Comment> comments) {
        return convertToDtoList(comments, null);
    }

    public static Collection<Comment> convertToDoList(final Collection<CommentDto> comments) {
        return convertToDoList(comments, null);
    }

    public static Collection<Comment> convertToDoList(final Collection<CommentDto> comments, final Message parentMessage) {
        if (comments == null) {
            return null;
        }
        return comments.stream().map(c -> convert(c, parentMessage)).collect(Collectors.toList());
    }

    public static Collection<CommentDto> convertToDtoList(final Collection<Comment> comments, final MessageDto parentMessage) {
        if (comments == null) {
            return null;
        }
        return comments.stream().map(c -> convert(c, parentMessage)).collect(Collectors.toList());
    }

}
