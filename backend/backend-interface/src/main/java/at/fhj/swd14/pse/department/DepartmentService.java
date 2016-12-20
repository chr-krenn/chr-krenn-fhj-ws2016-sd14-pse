package at.fhj.swd14.pse.department;

import java.util.Collection;

import javax.ejb.Remote;

/**
 * Department service class
 * @author Patrick Kainz
 *
 */
@Remote
public interface DepartmentService {
	/**
	 * Get all departments in existence
	 * @return Collection of all departments found
	 */
    Collection<DepartmentDto> findAll();
}
