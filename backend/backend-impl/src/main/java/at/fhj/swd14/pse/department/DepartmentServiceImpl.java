package at.fhj.swd14.pse.department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;

/**
 * Implementation of department service
 *
 * @author Patrick Kainz
 */
@Stateless
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);
    @EJB
    private DepartmentRepository depRepo;

    @Override
    public Collection<DepartmentDto> findAll() {
        try {
            LOGGER.trace("Finding all Departments");
            return DepartmentConverter.convertToDtoList(depRepo.findAll());
        } catch (Exception ex) {
            LOGGER.error("Error during finding of all departments", ex);
            throw new DepartmentServiceException("Departments could not be found");
        }
    }

}
