package at.fhj.swd14.pse.like;

import java.io.Serializable;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.user.UserDto;

/**
 * DTO for a like of a comment.
 *
 * @author Thomas
 */
public class CommentLikeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserDto liker;
    private CommentDto likedComment;

    /**
     * Construct a new like for a comment.
     *
     * @param liker   The user, who likes the comment.
     * @param comment The comment, which is liked.
     */
    public CommentLikeDto(UserDto liker, CommentDto comment) {
        this.liker = liker;
        this.likedComment = comment;
    }

    /**
     * Sets the person who likes a comment.
     *
     * @param liker The person who likes a comment.
     */
    public void setLiker(UserDto liker) {
        this.liker = liker;
    }

    /**
     * Returns the liker of a comment.
     *
     * @return The liker of a comment.
     */
    public UserDto getLiker() {
        return this.liker;
    }

    /**
     * Sets the liked comment.
     *
     * @param likedComment The comment to be liked.
     */
    public void setLikedComment(CommentDto likedComment) {
        this.likedComment = likedComment;
    }

    /**
     * Returns the liked comment.
     *
     * @return The liked comment.
     */
    public CommentDto getLikedComment() {
        return this.likedComment;
    }

    @Override
    public String toString() {
        return "CommentLikeDto" +
                "   liker: " + this.liker.getId() +
                "   comment: " + this.likedComment.getId();
    }
}
