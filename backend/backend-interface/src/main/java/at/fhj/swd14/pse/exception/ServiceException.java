package at.fhj.swd14.pse.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ServiceException(String message)
	{
		super(message);
	}

}
