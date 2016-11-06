package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.comment.Comment;

@Local
@Singleton
public class CommentRepository extends AbstractRepository<Comment> {

	public CommentRepository() {
        super(Comment.class);
    }

}
