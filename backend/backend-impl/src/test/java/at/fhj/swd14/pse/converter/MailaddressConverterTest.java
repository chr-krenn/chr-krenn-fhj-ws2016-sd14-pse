package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.person.Mailaddress;
import at.fhj.swd14.pse.person.MailaddressDto;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;

public class MailaddressConverterTest {

	@Test
	public void testDtoNull()
	{
		Mailaddress addr = MailaddressConverter.convert((MailaddressDto)null,null);
		Assert.assertNull(addr);
	}
	
	@Test
	public void testStatusNull()
	{
		MailaddressDto addr = MailaddressConverter.convert((Mailaddress)null,null);
		Assert.assertNull(addr);
	}
	
	@Test
	public void testDto()
	{
		MailaddressDto dto = new MailaddressDto();
		dto.setValue("hallo");
		Mailaddress addr = MailaddressConverter.convert(dto,new Person());
		Assert.assertEquals("hallo", addr.getValue());
	}
	
	@Test
	public void testStatus()
	{
		Mailaddress addr = new Mailaddress();
		addr.setValue("hallo");
		MailaddressDto dto = MailaddressConverter.convert(addr,new PersonDto());
		Assert.assertEquals("hallo", dto.getValue());
	}
	
	@Test
	public void testDtoListNull()
	{
		Collection<Mailaddress> values = MailaddressConverter.convertToDoList(null,new Person());
		Assert.assertNull(values);
	}
	
	@Test
	public void testDoListNull()
	{
		Collection<MailaddressDto> values = MailaddressConverter.convertToDtoList(null,new PersonDto());
		Assert.assertNull(values);
	}
	
	@Test
	public void testDtoList()
	{
		MailaddressDto dto = new MailaddressDto();
		dto.setValue("hallo");
		List<MailaddressDto> list = new LinkedList<>();
		list.add(dto);
		Collection<Mailaddress> values = MailaddressConverter.convertToDoList(list,new Person());
		Assert.assertTrue(values instanceof List);
		List<Mailaddress> valueList = (List<Mailaddress>)values;
		Assert.assertEquals(1, valueList.size());
		Assert.assertEquals("hallo", valueList.get(0).getValue());
	}
	
	@Test
	public void testDoList()
	{
		Mailaddress addr = new Mailaddress();
		addr.setValue("hallo");
		List<Mailaddress> list = new LinkedList<>();
		list.add(addr);
		Collection<MailaddressDto> values = MailaddressConverter.convertToDtoList(list, new PersonDto());
		Assert.assertTrue(values instanceof List);
		List<MailaddressDto> valueList = (List<MailaddressDto>)values;
		Assert.assertEquals(1, valueList.size());
		Assert.assertEquals("hallo", valueList.get(0).getValue());
	}
	
	
}
