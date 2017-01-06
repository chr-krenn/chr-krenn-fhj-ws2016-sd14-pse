package at.fhj.swd14.pse.like;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentConverter;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.comment.CommentRepository;
import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class CommentLikeServiceImpl implements CommentLikeService {

	private static final Logger LOGGER = LogManager.getLogger(CommentLikeServiceImpl.class);
	private static final String text = " could not be retrieved.";
	
    @EJB
    private CommentRepository commentRepository;
    @EJB
    private UserRepository userRepository;

    @Override
    public void save(CommentLikeDto commentLike) {
    	LOGGER.trace("Method save in CommentLikeService invoked.");
    	try {
    		if (commentLike == null){
    			LOGGER.error("Can not process comment like NULL");
        		throw new VerificationException("Can not process NULL as comment like");
    		}
    	CommentDto commentDTO = commentLike.getLikedComment();
        long commentId = CommentConverter.convert(commentDTO).getId();
        Comment comment = commentRepository.find(commentId);
        UserDto userDTO = commentLike.getLiker();
        long id = userDTO.getId();
        List<User> users = comment.getUsers();
        LikeServiceHelper helper = new LikeServiceHelper(users,userRepository);
        if (helper.isUserInList(id))
        {
        	helper.removeUserFromList();
        }
        else
        {
        	helper.insertUserInList(id);
        }
        helper = null;
        
        comment.setUsers(users);
        commentRepository.save(comment);
    	} catch(VerificationException e){
    		LOGGER.error("Illegal input from frontend." + e.getMessage(), e);
			throw new CommentLikeServiceException("Illegal input from frontend." + e.getMessage());
    	} catch(Exception e){
    		LOGGER.error("An error occurred while saving comment like.", e);
			throw new CommentLikeServiceException("Comment like could not be saved.");
    	}
    }

    @Override
    public CommentLikeDto getCommentLike(long userId, long commentId) {
    	LOGGER.trace("Method getCommentLike invoked");
    	try {
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
        
        return new CommentLikeDto(userDTO, commentDTO);
    	} catch(Exception e) {
    		LOGGER.error("An error occured while searching for comment like for: " + userId + " " + commentId, e);
    		throw new CommentLikeServiceException("Comment like for " + userId + " " + commentId + text);
    	}
    }

    @Override
    public List<CommentLikeDto> getCommentLikes(long commentId) {
    	LOGGER.trace("Method getCommentLikes invoked.");
    	try {
        CommentDto commentDTO = CommentConverter.convert(commentRepository.find(commentId));
        Comment comment = commentRepository.find(commentId);
        List<User> users = comment.getUsers();
        List<CommentLikeDto> commentLikes = new ArrayList<CommentLikeDto>();
        for (int i = 0; i < users.size(); i++) {
            CommentLikeDto commentLike = new CommentLikeDto(UserConverter.convert(users.get(i)), commentDTO);
            commentLikes.add(commentLike);
        }

        return commentLikes;
    	} catch (Exception e){
    		LOGGER.error("An error occured while searching for comment likes for: " + commentId, e);
    		throw new CommentLikeServiceException("Comment likes for " + commentId + text);
    	}
    }

    @Override
    public int getLikeCountForComment(long commentId) {
    	LOGGER.trace("Method getLikeCountForComment invoked.");
    	try {
        Comment comment = commentRepository.find(commentId);
        List<User> users = comment.getUsers();

        return users.size();
    	} catch (Exception e) {
    		LOGGER.error("An error occured while searching for like count for comment: " + commentId, e);
    		throw new CommentLikeServiceException("Like count for comment " + commentId + text);
    	}
    }

    @Override
    public int getLikeCountForUserForComments(long userId) {
    	LOGGER.trace("Method getLikeCountForUserForComments invoked.");
    	try {
        User user = userRepository.find(userId);
        List<Comment> comments = user.getComments();

        return comments.size();
    	} catch (Exception e) {
    		LOGGER.error("An error occured while searching for like count for comment for user: " + userId, e);
    		throw new CommentLikeServiceException("Like count for comments for user: " + userId + text);
    	}
    }

}
