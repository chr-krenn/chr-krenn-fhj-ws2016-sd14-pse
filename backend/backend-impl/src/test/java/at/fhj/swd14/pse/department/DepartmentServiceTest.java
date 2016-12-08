package at.fhj.swd14.pse.department;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.converter.DepartmentConverter;
import at.fhj.swd14.pse.person.DepartmentDtoTester;
import at.fhj.swd14.pse.repository.DepartmentRepository;


@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {
	@InjectMocks
	private DepartmentServiceImpl service;
	
	@Mock
	private DepartmentRepository depRepo;
	
	@Test
	public void testFindAll()
	{
		Department dep = new Department(1L);
		dep.setName("test");
		List<Department> deps = new LinkedList<Department>();
		deps.add(dep);
		Mockito.when(depRepo.findAll()).thenReturn(deps);
		List<DepartmentDto> dtos = (List<DepartmentDto>)service.findAll();
		Assert.assertEquals(1, dtos.size());
		DepartmentDtoTester.assertEquals(DepartmentConverter.convert(dep), dtos.get(0));
	}
	
	@Test(expected=DepartmentServiceException.class)
	public void testFindAllException()
	{
		Mockito.doThrow(Exception.class).when(depRepo).findAll();
		service.findAll();
	}
}
