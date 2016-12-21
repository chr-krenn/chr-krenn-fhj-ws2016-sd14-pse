package at.fhj.swd14.pse.exception;

/**
 * Exception pointing to an input verification error
 *
 * @author Patrick Kainz
 */
//TODO pkainz: delete this class and use {@link java.lang.IllegalArgumentException} instead
public class VerificationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VerificationException(String message) {
        super(message);
    }

}
