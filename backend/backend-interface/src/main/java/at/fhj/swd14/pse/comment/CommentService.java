package at.fhj.swd14.pse.comment;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CommentService {

    /**
     * Saves or updates the given message and returns the comment id.
     *
     * @param comment the comment to save
     * @return the id of the comments
     */
    long save(CommentDto comment);

    /**
     * Returns the comment for the given id.
     *
     * @param id the id of the comment
     * @return the comment for the given id, or null if none found
     */
    CommentDto find(long id);

    /**
     * Finds and returns all comments which were created by the given author.
     *
     * @param author the author's id
     * @return the comments of the author
     */
    List<CommentDto> findByAuthorId(long author);

    /**
     * Finds and returns all comments which were created for the given message.
     *
     * @param messageId the message's id
     * @return the comments for the message
     */
    List<CommentDto> findByMessageId(long messageId);
}
