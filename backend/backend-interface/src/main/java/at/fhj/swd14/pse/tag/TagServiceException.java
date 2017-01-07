package at.fhj.swd14.pse.tag;

import at.fhj.swd14.pse.exception.ServiceException;

/**
 * @author adelmann
 */
public class TagServiceException extends ServiceException{

    private static final long serialVersionUID = 1L;

    public TagServiceException(String message){
        super(message);
    }
}