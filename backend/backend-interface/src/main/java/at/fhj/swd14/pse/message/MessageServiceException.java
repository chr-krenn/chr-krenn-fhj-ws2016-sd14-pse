package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.exception.ServiceException;

/**
 * 
 * @author hartner
 *
 */
public class MessageServiceException extends ServiceException{

	private static final long serialVersionUID = 1L;
	
	public MessageServiceException(String message){
		super(message);
	}
}
