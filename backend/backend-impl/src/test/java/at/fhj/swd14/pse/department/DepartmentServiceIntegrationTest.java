package at.fhj.swd14.pse.department;

import at.fhj.swd14.pse.base.IntegrationTestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DepartmentServiceIntegrationTest {

    private DepartmentService service;

    @Before
    public void setup() throws NamingException {
        service = IntegrationTestUtil.getService(DepartmentService.class);
    }

    @Test
    @Ignore
    public void testFindAll() {
        Collection<DepartmentDto> dtos = service.findAll();
        Assert.assertNotNull(dtos);
        Assert.assertEquals(2, dtos.size());
        List<DepartmentDto> dtoList = new ArrayList<>(dtos);
        int found = 0;
        for (DepartmentDto dto : dtoList) {
            if (dto.getName().equals("IT"))
                found |= 1;
            if (dto.getName().equals("Accounting"))
                found |= 2;
        }
        Assert.assertEquals(3, found);
    }

}
