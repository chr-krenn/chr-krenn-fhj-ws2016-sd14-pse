package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.department.DepartmentDto;

public class DepartmentConverterTest {

	@Test
	public void testDtoNull()
	{
		Department dep = DepartmentConverter.convert((DepartmentDto)null);
		Assert.assertNull(dep);
	}
	
	@Test
	public void testDepartmentNull()
	{
		DepartmentDto dep = DepartmentConverter.convert((Department)null);
		Assert.assertNull(dep);
	}
	
	@Test
	public void testDto()
	{
		DepartmentDto dto = new DepartmentDto(1L);
		dto.setName("hallo");
		Department dep = DepartmentConverter.convert(dto);
		Assert.assertEquals("hallo", dep.getName());
	}
	
	@Test
	public void testDepartment()
	{
		Department dep = new Department();
		dep.setName("hallo");
		DepartmentDto dto = DepartmentConverter.convert(dep);
		Assert.assertEquals("hallo", dto.getName());
	}
	
	@Test
	public void testDtoListNull()
	{
		Collection<Department> values = DepartmentConverter.convertToDoList((Collection<DepartmentDto>)null);
		Assert.assertNull(values);
	}
	
	@Test
	public void testDoListNull()
	{
		Collection<DepartmentDto> values = DepartmentConverter.convertToDtoList((Collection<Department>)null);
		Assert.assertNull(values);
	}
	
	@Test
	public void testDtoList()
	{
		DepartmentDto dto = new DepartmentDto(1L);
		dto.setName("hallo");
		List<DepartmentDto> list = new LinkedList<DepartmentDto>();
		list.add(dto);
		Collection<Department> values = DepartmentConverter.convertToDoList(list);
		Assert.assertTrue(values instanceof List);
		List<Department> valueList = (List<Department>)values;
		Assert.assertEquals(1, valueList.size());
		Assert.assertEquals("hallo", valueList.get(0).getName());
	}
	
	@Test
	public void testDoList()
	{
		Department dep = new Department();
		dep.setName("hallo");
		List<Department> list = new LinkedList<Department>();
		list.add(dep);
		Collection<DepartmentDto> values = DepartmentConverter.convertToDtoList(list);
		Assert.assertTrue(values instanceof List);
		List<DepartmentDto> valueList = (List<DepartmentDto>)values;
		Assert.assertEquals(1, valueList.size());
		Assert.assertEquals("hallo", valueList.get(0).getName());
	}
	
	
}
