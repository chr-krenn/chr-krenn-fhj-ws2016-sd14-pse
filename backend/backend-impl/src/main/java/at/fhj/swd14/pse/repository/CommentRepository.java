package at.fhj.swd14.pse.repository;

import javax.ejb.Stateless;

import at.fhj.swd14.pse.comment.Comment;

@Stateless
public class CommentRepository extends AbstractRepository<Comment> {

	public CommentRepository() {
        super(Comment.class);
    }

}
