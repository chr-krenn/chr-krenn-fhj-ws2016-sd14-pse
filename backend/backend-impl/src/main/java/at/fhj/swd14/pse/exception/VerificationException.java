package at.fhj.swd14.pse.exception;

/**
 * Exception pointing to an input verification error
 *
 * @author Patrick Kainz
 */
//NOT_TODO pkainz: delete this class and use {@link java.lang.IllegalArgumentException} instead
//NO: Teiniker said he doesn't want IllegalArgumentException...
public class VerificationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VerificationException(String message) {
        super(message);
    }

}
