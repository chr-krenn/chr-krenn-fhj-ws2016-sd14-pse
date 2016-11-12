package at.fhj.swd14.pse.like;

import javax.ejb.Remote;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.comment.CommentDto;
import java.util.List;

@Remote
public interface CommentLikeService {

	public void save(CommentLikeDto commentLike);
	
	public CommentLikeDto getCommentLike(UserDto user,CommentDto comment);
	
	public List<CommentLikeDto> getCommentLikes(CommentDto comment);
	
	public int getLikeCountForComment(CommentDto comment);
	
	public int getLikeCountForUserForComments(UserDto user);
	
}
