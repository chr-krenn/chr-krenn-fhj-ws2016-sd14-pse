package at.fhj.swd14.pse.like;

import at.fhj.swd14.pse.exception.ServiceException;
/**
 * 
 * @author klampflt
 *
 */
public class CommentLikeServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public CommentLikeServiceException(String message){
		super(message);
	}
}
