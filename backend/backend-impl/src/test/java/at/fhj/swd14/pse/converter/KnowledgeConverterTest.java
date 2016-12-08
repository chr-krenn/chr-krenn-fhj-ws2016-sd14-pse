package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.person.Knowledge;
import at.fhj.swd14.pse.person.KnowledgeDto;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;

public class KnowledgeConverterTest {

	@Test
	public void testDtoNull()
	{
		Knowledge know = KnowledgeConverter.convert((KnowledgeDto)null,null);
		Assert.assertNull(know);
	}
	
	@Test
	public void testStatusNull()
	{
		KnowledgeDto know = KnowledgeConverter.convert((Knowledge)null,null);
		Assert.assertNull(know);
	}
	
	@Test
	public void testDto()
	{
		KnowledgeDto dto = new KnowledgeDto();
		dto.setValue("hallo");
		Knowledge know = KnowledgeConverter.convert(dto,new Person());
		Assert.assertEquals("hallo", know.getValue());
	}
	
	@Test
	public void testStatus()
	{
		Knowledge know = new Knowledge();
		know.setValue("hallo");
		KnowledgeDto dto = KnowledgeConverter.convert(know,new PersonDto());
		Assert.assertEquals("hallo", dto.getValue());
	}
	
	@Test
	public void testDtoListNull()
	{
		Collection<Knowledge> values = KnowledgeConverter.convertToDoList((Collection<KnowledgeDto>)null,new Person());
		Assert.assertNull(values);
	}
	
	@Test
	public void testDoListNull()
	{
		Collection<KnowledgeDto> values = KnowledgeConverter.convertToDtoList((Collection<Knowledge>)null,new PersonDto());
		Assert.assertNull(values);
	}
	
	@Test
	public void testDtoList()
	{
		KnowledgeDto dto = new KnowledgeDto();
		dto.setValue("hallo");
		List<KnowledgeDto> list = new LinkedList<KnowledgeDto>();
		list.add(dto);
		Collection<Knowledge> values = KnowledgeConverter.convertToDoList(list,new Person());
		Assert.assertTrue(values instanceof List);
		List<Knowledge> valueList = (List<Knowledge>)values;
		Assert.assertEquals(1, valueList.size());
		Assert.assertEquals("hallo", valueList.get(0).getValue());
	}
	
	@Test
	public void testDoList()
	{
		Knowledge know = new Knowledge();
		know.setValue("hallo");
		List<Knowledge> list = new LinkedList<Knowledge>();
		list.add(know);
		Collection<KnowledgeDto> values = KnowledgeConverter.convertToDtoList(list, new PersonDto());
		Assert.assertTrue(values instanceof List);
		List<KnowledgeDto> valueList = (List<KnowledgeDto>)values;
		Assert.assertEquals(1, valueList.size());
		Assert.assertEquals("hallo", valueList.get(0).getValue());
	}
	
	
}
