package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.department.DepartmentDto;
import org.junit.Assert;

public class DepartmentDtoTester {

    public DepartmentDtoTester() {
        //gets rid of the default constructor
    }

    public static void assertEquals(DepartmentDto expected, DepartmentDto actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

}
