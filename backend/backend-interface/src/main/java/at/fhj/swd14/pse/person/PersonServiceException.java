package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.exception.ServiceException;

public class PersonServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public PersonServiceException(String message) {
		super(message);
	}

}
