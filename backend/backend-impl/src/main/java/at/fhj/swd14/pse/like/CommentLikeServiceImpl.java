package at.fhj.swd14.pse.like;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentConverter;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.comment.CommentRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserRepository;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;

public class CommentLikeServiceImpl implements CommentLikeService {

    @EJB
    private CommentRepository commentRepository;
    @EJB
    private UserRepository userRepository;

    @Override
    public void save(CommentLikeDto commentLike) {
        CommentDto commentDTO = commentLike.getLikedComment();
        UserDto userDTO = commentLike.getLiker();
        commentRepository.save(CommentConverter.convert(commentDTO));
        userRepository.save(UserConverter.convert(userDTO));
    }

    @Override
    public CommentLikeDto getCommentLike(long userId, long commentId) {
        UserDto userDTO = UserConverter.convert(userRepository.find(userId));
        CommentDto commentDTO = CommentConverter.convert(commentRepository.find(commentId));
        CommentLikeDto commentLikeDTO = new CommentLikeDto(userDTO, commentDTO);

        return commentLikeDTO;
    }

    @Override
    public List<CommentLikeDto> getCommentLikes(long commentId) {
        CommentDto commentDTO = CommentConverter.convert(commentRepository.find(commentId));
        Comment comment = commentRepository.find(commentId);
        List<User> users = comment.getUsers();
        List<CommentLikeDto> commentLikes = new ArrayList<CommentLikeDto>();
        for (int i = 0; i < users.size(); i++) {
            CommentLikeDto commentLike = new CommentLikeDto(UserConverter.convert(users.get(i)), commentDTO);
            commentLikes.add(commentLike);
        }

        return commentLikes;
    }

    @Override
    public int getLikeCountForComment(long commentId) {
        Comment comment = commentRepository.find(commentId);
        List<User> users = comment.getUsers();

        return users.size();
    }

    @Override
    public int getLikeCountForUserForComments(long userId) {
        User user = userRepository.find(userId);
        List<Comment> comments = user.getComments();

        return comments.size();
    }

}
