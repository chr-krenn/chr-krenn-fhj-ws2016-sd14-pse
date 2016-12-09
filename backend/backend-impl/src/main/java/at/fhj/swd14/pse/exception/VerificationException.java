package at.fhj.swd14.pse.exception;

/**
 * Exception pointing to an input verification error
 * @author Patrick Kainz
 *
 */
public class VerificationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VerificationException(String message)
	{
		super(message);
	}
	
}
