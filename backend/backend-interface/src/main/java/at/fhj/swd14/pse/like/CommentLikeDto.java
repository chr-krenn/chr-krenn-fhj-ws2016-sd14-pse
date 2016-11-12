package at.fhj.swd14.pse.like;

import java.io.Serializable;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.user.UserDto;
/**
 * Dto for a like of a comment.
 * @author Thomas
 *
 */
public class CommentLikeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserDto liker;
	private CommentDto likedComment;
	
	/**
     * Construct a new like for a comment.
     * @param liker The user, who likes the comment.
     * @param comment The comment, which is liked.
     */
    public CommentLikeDto(UserDto liker,CommentDto comment) {
    	this.liker = liker;
    	this.likedComment = comment;
    }
    
    public void setLiker(UserDto liker) {
    	this.liker = liker;
    }
    
    public UserDto getLiker() {
    	return this.liker;
    }
    
    public void setLikedComment(CommentDto likedComment) {
    	this.likedComment = likedComment;
    }
    
    public CommentDto getLikedComment() {
    	return this.likedComment;
    }
    
    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("CommentLikeDto");
    	sb.append("   liker: " + this.liker.getId());
    	sb.append("   comment: " + this.likedComment.getId());
    	
    	return sb.toString();
    }
}
