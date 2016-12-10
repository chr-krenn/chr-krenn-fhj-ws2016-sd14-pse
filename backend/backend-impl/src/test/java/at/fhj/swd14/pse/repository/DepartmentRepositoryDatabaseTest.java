package at.fhj.swd14.pse.repository;

import org.junit.Assert;

import at.fhj.swd14.pse.department.Department;

public class DepartmentRepositoryDatabaseTest extends AbstractRepositoryDatabaseIDTest<Department> {

	private DepartmentRepository repo;
	

	public DepartmentRepositoryDatabaseTest() {
		super(Department.class);
	}
	
	@Override
	protected long getDummyId(Department dummy) {
		return dummy.getId();
	}

	@Override
	protected AbstractRepository<Department> getRepository() {
		if(repo==null)
		{
			repo = new DepartmentRepository();
		}
		return repo;
	}

	@Override
	protected Department createDummyEntity() {
		Department dep = new Department();
		dep.setName("test");
		return dep;
	}

	@Override
	protected void assertEquals(Department expected, Department actual) {
		Assert.assertEquals(expected.getId(),actual.getId());
		Assert.assertEquals(expected.getName(),actual.getName());
	}

	@Override
	protected Department modifyDummy(Department dummy) {
		dummy.setName("test2");
		return dummy;
	}

	@Override
	protected void copyDummyPK(Department destination, Department source) {
		destination.setId(source.getId());
	}

}
