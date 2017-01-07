package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.department.DepartmentDto;
import org.junit.Assert;

public final class DepartmentAssert {

    private DepartmentAssert() {
    }

    public static void assertEquals(DepartmentDto expected, DepartmentDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

}
