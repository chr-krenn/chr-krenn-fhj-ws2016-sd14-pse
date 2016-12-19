package at.fhj.swd14.pse.like;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.converter.CommentConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.repository.CommentRepository;
import at.fhj.swd14.pse.repository.UserRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

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
		CommentLikeDto commentLikeDTO = new CommentLikeDto(userDTO,commentDTO);
		
		return commentLikeDTO;
	}

	@Override
	public List<CommentLikeDto> getCommentLikes(long commentId) {
		CommentDto commentDTO = CommentConverter.convert(commentRepository.find(commentId));
		Comment comment = commentRepository.find(commentId);
		List<User> users = comment.getUsers();
		List<CommentLikeDto> commentLikes = new ArrayList<CommentLikeDto>();
		for (int i = 0; i < users.size(); i ++)
		{
			CommentLikeDto commentLike = new CommentLikeDto(UserConverter.convert(users.get(i)),commentDTO);
			commentLikes.add(commentLike);
		}
		
		return commentLikes;
	}

	@Override
	public int getLikeCountForComment(long commentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLikeCountForUserForComments(long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
