package at.fhj.swd14.pse.department;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd14.pse.person.DepartmentAssert;

public class DepartmentConverterTest {

	private DepartmentConverter converter;
	private DepartmentDto dto1;
	@Before
	public void setup()
	{
		List<DepartmentDto> dtos = new ArrayList<DepartmentDto>();
		 dto1 = new DepartmentDto(1L);
		dto1.setName("test1");
		DepartmentDto dto2 = new DepartmentDto(2L);
		dto2.setName("test2");
		dtos.add(dto1);
		dtos.add(dto2);
		converter = new DepartmentConverter();
		DepartmentConverter.setDepartments(dtos);
	}
	
	@Test
	public void testAsObject()
	{
		DepartmentDto dto = (DepartmentDto)converter.getAsObject(null, null, "1");
		Assert.assertNotNull(dto);
		DepartmentAssert.assertEquals(dto1, dto);
	}
	
	@Test
	public void testAsObjectNotExists()
	{
		DepartmentDto dto = (DepartmentDto)converter.getAsObject(null, null, "3");
		Assert.assertNull(dto);
	}
	
	@Test
	public void testAsString()
	{
		String result = converter.getAsString(null, null, dto1);
		Assert.assertEquals(dto1.getId().toString(), result);
	}
	
	@Test
	public void testAsStringNull()
	{
		String result = converter.getAsString(null, null, null);
		Assert.assertEquals("", result);
	}
	
}
