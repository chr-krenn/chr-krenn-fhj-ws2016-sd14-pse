package at.fhj.swd14.pse.department;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.converter.DepartmentConverter;
import at.fhj.swd14.pse.repository.DepartmentRepository;

/**
 * Implementation of department service
 * @author Patrick Kainz
 *
 */
@Stateless
public class DepartmentServiceImpl implements DepartmentService{

	private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);
	@EJB
	private DepartmentRepository depRepo;
	
	@Override
	public Collection<DepartmentDto> findAll() {
		LOGGER.trace("Finding all Departments");
		return DepartmentConverter.convertToDtoList(depRepo.findAll());
	}

}
