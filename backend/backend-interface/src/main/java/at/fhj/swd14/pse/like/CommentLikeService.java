package at.fhj.swd14.pse.like;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CommentLikeService {
    /**
     * Saves a comment like to the database.
     *
     * @param commentLike
     */
    void save(CommentLikeDto commentLike);

    /**
     * Returns the comment like data transfer object.
     *
     * @param userId    The id of the user.
     * @param commentId The id of the comment.
     * @return The comment like data transfer object.
     */
    CommentLikeDto getCommentLike(long userId, long commentId);

    /**
     * Returns the comment like data transfer objects for a given comment.
     *
     * @param commentId The id of the comment.
     * @return The list of comment like data transfer objects for a comment.
     */
    List<CommentLikeDto> getCommentLikes(long commentId);

    /**
     * Returns the count of likes for a given comment.
     *
     * @param commentId The id of the comment.
     * @return The number of likes for a comment.
     */
    int getLikeCountForComment(long commentId);

    /**
     * Returns the number of likes of a user for comments.
     *
     * @param userId The id of the user.
     * @return The count of likes for a user for comments.
     */
    int getLikeCountForUserForComments(long userId);

}
