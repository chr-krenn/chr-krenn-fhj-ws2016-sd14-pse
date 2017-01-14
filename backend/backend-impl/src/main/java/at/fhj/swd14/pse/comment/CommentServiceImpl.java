package at.fhj.swd14.pse.comment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LogManager.getLogger(CommentServiceImpl.class);

    @EJB
    private CommentRepository commentRepository;

    @Override
    public long save(CommentDto comment) {
        LOGGER.trace("Saving comment");
        try {
            Comment doComment = CommentConverter.convert(comment);
            commentRepository.save(doComment);
            LOGGER.info("Comment {} saved", doComment.getId());
            return doComment.getId();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new CommentServiceException("Failed to save comment");
        }
    }

    @Override
    public CommentDto find(long id) {
        LOGGER.trace("Finding comment with id {}", id);
        try {
            return CommentConverter.convert(commentRepository.find(id));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new CommentServiceException("Failed to save comment");
        }
    }

    @Override
    public List<CommentDto> findByAuthorId(long authorUserId) {
        LOGGER.trace("Finding comments by author id {}", authorUserId);
        try {
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("authorUserId", authorUserId);
            return executeNamedQuery("Comment.findByAuthorId", parameter);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new CommentServiceException("Failed to find comments");
        }
    }

    @Override
    public List<CommentDto> findByMessageId(long messageId) {
        LOGGER.trace("Finding comments by message id {}", messageId);
        try {
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("commentId", messageId);
            return executeNamedQuery("Comment.findByCommunityId", parameter);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new CommentServiceException("Failed to find comments");
        }
    }

    private List<CommentDto> executeNamedQuery(String name, Map<String, Object> parameter) {
        return new ArrayList<>(CommentConverter.convertToDtoList(commentRepository.executeNamedQuery(name, parameter)));
    }
}
