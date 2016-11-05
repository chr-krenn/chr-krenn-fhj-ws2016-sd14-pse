package at.fhj.swd14.pse.repository;

import javax.ejb.Stateless;

import at.fhj.swd14.pse.department.Department;

@Stateless
public class DepartmentRepository extends AbstractRepository<Department> {

	public DepartmentRepository()
	{
		super(Department.class);
	}
	
}
