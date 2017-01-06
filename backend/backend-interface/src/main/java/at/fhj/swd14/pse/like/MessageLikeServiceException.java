package at.fhj.swd14.pse.like;

import at.fhj.swd14.pse.exception.ServiceException;
/**
 * 
 * @author klampflt
 *
 */
public class MessageLikeServiceException extends ServiceException {
	private static final long serialVersionUID = 1L;
	
	public MessageLikeServiceException(String message){
		super(message);
	}
}
