package at.fhj.swd14.pse.department;

import at.fhj.swd14.pse.exception.ServiceException;

/**
 * General exception for any problems in DepartmentService
 *
 * @author Patrick Kainz
 */
public class DepartmentServiceException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public DepartmentServiceException(String message) {
        super(message);
    }

}
