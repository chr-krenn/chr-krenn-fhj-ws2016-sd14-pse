package at.fhj.swd14.pse.department;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd14.pse.base.AbstractIntegrationTest;
import org.junit.Assert;

public class DepartmentServiceIntegrationTest extends AbstractIntegrationTest{
	
	private DepartmentService service;
	
	@Before
	public void setup() throws NamingException
	{
		super.setup();
		service = (DepartmentService)context.lookup("ejb:backend-assembly-1.1-SNAPSHOT/backend-impl-1.1-SNAPSHOT//DepartmentServiceImpl!at.fhj.swd14.pse.department.DepartmentService");
	}
	
	@Test
	public void testFindAll()
	{
		Collection<DepartmentDto> dtos = service.findAll();
		Assert.assertNotNull(dtos);
		Assert.assertEquals(2, dtos.size());
		List<DepartmentDto> dtoList = new ArrayList<DepartmentDto>(dtos);
		int found = 0;
		for(DepartmentDto dto : dtoList)
		{
			if(dto.getName().equals("IT"))
				found|=1;
			if(dto.getName().equals("Accounting"))
				found|=2;
		}
		Assert.assertEquals(3,found);
	}
	
}
