package at.fhj.swd14.pse.department;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.converter.DepartmentConverter;
import at.fhj.swd14.pse.repository.DepartmentRepository;

/**
 * Implementation of department service
 * @author Patrick Kainz
 *
 */
@Stateless
public class DepartmentServiceImpl implements DepartmentService{

	@EJB
	private DepartmentRepository depRepo;
	
	@Override
	public Collection<DepartmentDto> findAll() {
		return DepartmentConverter.convertToDtoList(depRepo.findAll());
	}

}
