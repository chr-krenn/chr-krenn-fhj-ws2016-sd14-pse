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
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class CommentLikeServiceImpl implements CommentLikeService {

    @EJB
    private CommentRepository commentRepository;
    @EJB
    private UserRepository userRepository;

    @Override
    public void save(CommentLikeDto commentLike) {
    	CommentDto commentDTO = commentLike.getLikedComment();
        long commentId = CommentConverter.convert(commentDTO).getId();
        Comment comment = commentRepository.find(commentId);
        UserDto userDTO = commentLike.getLiker();
        long id = userDTO.getId();
        List<User> users = comment.getUsers();
        boolean isUserInList = false;
        int positionInList = 0;
        for (int i = 0; i < users.size(); i++)
        {
        	User user = users.get(i);
        	long userId = user.getId();
        	if (id == userId)
        	{
        		isUserInList = true; // user has already liked the comment
        		positionInList = i;
        	}
        }
        if (isUserInList == true) // remove user from list
        {
        	users.remove(positionInList);
        }
        else // insert user in list
        {
        	User user = userRepository.find(id); // find user in database
        	users.add(user);
        }
        comment.setUsers(users);
        commentRepository.save(comment);
    }

    @Override
    public CommentLikeDto getCommentLike(long userId, long commentId) {
    	CommentDto commentDTO = CommentConverter.convert(commentRepository.find(commentId));
        Comment comment = commentRepository.find(commentId);
        UserDto userDTO = null;
        List<User> users = comment.getUsers();
        User userAlreadyLiked = null;
        for (int i = 0; i < users.size(); i++)
        {
        	User user = users.get(i);
        	long id = user.getId();
        	if (id == userId)
        	{
        		userAlreadyLiked = user;
        	}
        }
        if (userAlreadyLiked != null)
        {
        	userDTO = UserConverter.convert(userAlreadyLiked);
        }
        CommentLikeDto commentLike = new CommentLikeDto(userDTO, commentDTO);

        return commentLike;
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
