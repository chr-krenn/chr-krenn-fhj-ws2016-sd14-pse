package at.fhj.swd14.pse.comment;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CommentServiceImpl implements CommentService {
    @Override
    public long save(CommentDto comment) {
        return 0;
    }

    @Override
    public CommentDto find(long id) {
        return null;
    }

    @Override
    public List<CommentDto> findByCreatorId(long author) {
        return null;
    }

    @Override
    public List<CommentDto> findByMessageId(long messageId) {
        return null;
    }
}
