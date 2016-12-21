package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.department.Department;
import org.junit.Assert;

public class DepartmentRepositoryImplIntegrationTest extends AbstractRepositoryIDIntegrationTest<Department> {

    public DepartmentRepositoryImplIntegrationTest() {
        super(Department.class);
    }

    @Override
    protected long getDummyId(Department dummy) {
        return dummy.getId();
    }

    @Override
    protected AbstractRepository<Department> getRepository() {
        return new DepartmentRepositoryImpl();
    }

    @Override
    protected Department createDummyEntity() {
        Department dep = new Department();
        dep.setName("test");
        return dep;
    }

    @Override
    protected void assertEquals(Department expected, Department actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
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
