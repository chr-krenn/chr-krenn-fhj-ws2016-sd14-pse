package at.fhj.swd14.pse.like;

import java.util.List;
import javax.ejb.EJB;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.converter.CommentConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.repository.CommentRepository;
import at.fhj.swd14.pse.repository.UserRepository;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentLikeDto> getCommentLikes(long commentId) {
		// TODO Auto-generated method stub
		return null;
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
