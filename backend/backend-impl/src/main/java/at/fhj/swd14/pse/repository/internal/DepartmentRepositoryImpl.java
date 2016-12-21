package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.department.DepartmentRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

/**
 * Repository for Department Entities
 *
 * @author Patrick Kainz
 */
@Local
@Singleton
public class DepartmentRepositoryImpl
        extends AbstractRepository<Department>
        implements DepartmentRepository {

    public DepartmentRepositoryImpl() {
        super(Department.class);
    }

}
