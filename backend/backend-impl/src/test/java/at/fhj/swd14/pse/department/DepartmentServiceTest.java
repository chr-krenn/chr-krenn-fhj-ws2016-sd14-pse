package at.fhj.swd14.pse.department;

import at.fhj.swd14.pse.person.DepartmentAssert;
import at.fhj.swd14.pse.repository.internal.DepartmentRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {
    @InjectMocks
    private DepartmentServiceImpl service;

    @Mock
    private DepartmentRepositoryImpl depRepo;

    @Test
    public void testFindAll() {
        Department dep = new Department(1L);
        dep.setName("test");
        List<Department> deps = new LinkedList<>();
        deps.add(dep);
        Mockito.when(depRepo.findAll()).thenReturn(deps);
        List<DepartmentDto> dtos = (List<DepartmentDto>) service.findAll();
        Assert.assertEquals(1, dtos.size());
        DepartmentAssert.assertEquals(DepartmentConverter.convert(dep), dtos.get(0));
    }

    @Test(expected = DepartmentServiceException.class)
    public void testFindAllException() {
        Mockito.doThrow(Exception.class).when(depRepo).findAll();
        service.findAll();
    }
}
