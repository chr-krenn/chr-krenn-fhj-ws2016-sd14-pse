package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.exception.ServiceException;

/**
 * Exception for any problems occuring in the PersonService
 * @author Patrick Kainz
 *
 */
public class PersonServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public PersonServiceException(String message) {
		super(message);
	}

}
