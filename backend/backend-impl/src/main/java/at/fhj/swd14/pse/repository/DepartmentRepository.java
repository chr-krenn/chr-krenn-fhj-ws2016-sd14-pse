package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.department.Department;

/**
 * Repository for Department Entities
 * @author Patrick Kainz
 *
 */
@Local
@Singleton
public class DepartmentRepository extends AbstractRepository<Department> {

	public DepartmentRepository()
	{
		super(Department.class);
	}
	
}
