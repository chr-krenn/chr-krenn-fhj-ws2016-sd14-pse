package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.exception.ServiceException;

public class NewsServiceException extends ServiceException {
    public NewsServiceException(String message) {
        super(message);
    }
}
