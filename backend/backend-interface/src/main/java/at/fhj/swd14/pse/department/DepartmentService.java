package at.fhj.swd14.pse.department;

import javax.ejb.Remote;
import java.util.Collection;

/**
 * Department service class
 * @author Patrick Kainz
 *
 */
@Remote
public interface DepartmentService {
	public Collection<DepartmentDto> findAll();
}
