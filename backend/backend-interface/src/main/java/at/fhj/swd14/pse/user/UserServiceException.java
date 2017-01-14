package at.fhj.swd14.pse.user;

import at.fhj.swd14.pse.exception.ServiceException;

public class UserServiceException extends ServiceException {
    public UserServiceException(String message) {
        super(message);
    }
}
