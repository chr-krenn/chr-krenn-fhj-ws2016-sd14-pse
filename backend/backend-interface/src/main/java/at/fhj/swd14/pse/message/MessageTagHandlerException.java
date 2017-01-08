package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.exception.ServiceException;

/**
 * 
 * @author Gölles-Haas
 *
 */
public class MessageTagHandlerException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public MessageTagHandlerException(String message){
		super(message);
	}
}


