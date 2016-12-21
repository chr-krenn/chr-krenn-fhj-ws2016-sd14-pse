package at.fhj.swd14.pse.exception;

/**
 * General exception for any problems in any service class
 *
 * @author Patrick Kainz
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

}
