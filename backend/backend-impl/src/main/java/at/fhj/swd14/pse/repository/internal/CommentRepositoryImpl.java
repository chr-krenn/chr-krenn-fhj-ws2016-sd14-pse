package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

@Local
@Singleton
public class CommentRepositoryImpl
        extends AbstractRepository<Comment>
        implements CommentRepository {

    public CommentRepositoryImpl() {
        super(Comment.class);
    }

}
