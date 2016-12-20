package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.Phonenumber;
import at.fhj.swd14.pse.person.PhonenumberDto;

public class PhonenumberConverterTest {

	@Test
	public void testDtoNull()
	{
		Phonenumber number = PhonenumberConverter.convert((PhonenumberDto)null,null);
		Assert.assertNull(number);
	}
	
	@Test
	public void testStatusNull()
	{
		PhonenumberDto number = PhonenumberConverter.convert((Phonenumber)null,null);
		Assert.assertNull(number);
	}
	
	@Test
	public void testDto()
	{
		PhonenumberDto dto = new PhonenumberDto();
		dto.setValue("hallo");
		Phonenumber number = PhonenumberConverter.convert(dto,new Person());
		Assert.assertEquals("hallo", number.getValue());
	}
	
	@Test
	public void testStatus()
	{
		Phonenumber number = new Phonenumber();
		number.setValue("hallo");
		PhonenumberDto dto = PhonenumberConverter.convert(number,new PersonDto());
		Assert.assertEquals("hallo", dto.getValue());
	}
	
	@Test
	public void testDtoListNull()
	{
		Collection<Phonenumber> values = PhonenumberConverter.convertToDoList(null,new Person());
		Assert.assertNull(values);
	}
	
	@Test
	public void testDoListNull()
	{
		Collection<PhonenumberDto> values = PhonenumberConverter.convertToDtoList(null,new PersonDto());
		Assert.assertNull(values);
	}
	
	@Test
	public void testDtoList()
	{
		PhonenumberDto dto = new PhonenumberDto();
		dto.setValue("hallo");
		List<PhonenumberDto> list = new LinkedList<>();
		list.add(dto);
		Collection<Phonenumber> values = PhonenumberConverter.convertToDoList(list,new Person());
		Assert.assertTrue(values instanceof List);
		List<Phonenumber> valueList = (List<Phonenumber>)values;
		Assert.assertEquals(1, valueList.size());
		Assert.assertEquals("hallo", valueList.get(0).getValue());
	}
	
	@Test
	public void testDoList()
	{
		Phonenumber number = new Phonenumber();
		number.setValue("hallo");
		List<Phonenumber> list = new LinkedList<>();
		list.add(number);
		Collection<PhonenumberDto> values = PhonenumberConverter.convertToDtoList(list, new PersonDto());
		Assert.assertTrue(values instanceof List);
		List<PhonenumberDto> valueList = (List<PhonenumberDto>)values;
		Assert.assertEquals(1, valueList.size());
		Assert.assertEquals("hallo", valueList.get(0).getValue());
	}
	
	
}
