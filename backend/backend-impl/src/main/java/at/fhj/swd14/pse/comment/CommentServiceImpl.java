package at.fhj.swd14.pse.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.converter.CommentConverter;
import at.fhj.swd14.pse.repository.CommentRepository;

@Stateless
public class CommentServiceImpl implements CommentService {

    @EJB
    private CommentRepository commentRepository;

    @Override
    public long save(CommentDto comment) {
        Comment doComment = CommentConverter.convert(comment);
        commentRepository.save(doComment);
        return doComment.getId();
    }

    @Override
    public CommentDto find(long id) {
        return CommentConverter.convert(commentRepository.find(id));
    }

    @Override
    public List<CommentDto> findByAuthorId(long authorUserId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("authorUserId", authorUserId);
        return executeNamedQuery("Comment.findByAuthorId", parameter);
    }

    @Override
    public List<CommentDto> findByMessageId(long messageId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("commentId", messageId);
        return executeNamedQuery("Comment.findByCommunityId", parameter);
    }

    private List<CommentDto> executeNamedQuery(String name, Map<String, Object> parameter) {
        return new ArrayList<>(CommentConverter.convertToDtoList(commentRepository.executeNamedQuery(name, parameter)));
    }
}
