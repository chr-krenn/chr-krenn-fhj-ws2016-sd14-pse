package at.fhj.swd14.pse.like;

import java.io.Serializable;
import java.time.Instant;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.user.UserDto;
/**
 * Data transfer object for an like.
 * @author Thomas
 *
 */
public class LikeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Instant created;
    private Instant modified;
    private UserDto liker;
    private CommentDto likedComment;
    private MessageDto likedMessage;
    /**
     * Construct a new like for a message.
     * @param id The id of the like.
     * @param liker The user, who likes the message.
     * @param message The message, which is liked.
     */
    public LikeDto(Long id,UserDto liker,MessageDto message) {
    	this.id = id;
    	this.liker = liker;
    	this.likedMessage = message;
    	this.likedComment = null;
    }
    /**
     * Construct a new like for a comment.
     * @param id The id of the like.
     * @param liker The user, who likes the comment.
     * @param comment The comment, which is liked.
     */
    public LikeDto(Long id,UserDto liker,CommentDto comment) {
    	this.id = id;
    	this.liker = liker;
    	this.likedMessage = null;
    	this.likedComment = comment;
    }
    
    public UserDto getLiker() {
		return liker;
	}
    
    public void setLiker(UserDto user) {
    	this.liker = user;   	
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public Instant getTimeCreated() {
    	return this.created;
    }
    
    public void setTimeCreated(Instant created) {
    	this.created = created;
    }
    
    public Instant getTimeModified() {
    	return this.modified;
    }
    
    public void setTimeModified(Instant modified) {
    	this.modified = modified;
    }
    
    public CommentDto getLikedComment() {
    	return this.likedComment;
    }
    
    public void setLikedComment(CommentDto comment) {
    	this.likedComment = comment;
    }
    
    public MessageDto getLikedMessage() {
    	return this.likedMessage;
    }
    
    public void setLikedMessage(MessageDto message) {
    	this.likedMessage = message;
    }
    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("LikeDto");
    	sb.append("   Id: " + id);
    	sb.append("   Time created: " + created.toString());
    	sb.append("   Time modified: " + modified.toString());
    	
    	return sb.toString();
    }
}
