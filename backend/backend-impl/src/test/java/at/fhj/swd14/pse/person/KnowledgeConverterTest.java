package at.fhj.swd14.pse.person;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class KnowledgeConverterTest {

    @Test
    public void testDtoNull() {
        Knowledge know = KnowledgeConverter.convert((KnowledgeDto) null, null);
        Assert.assertNull(know);
    }

    @Test
    public void testStatusNull() {
        KnowledgeDto know = KnowledgeConverter.convert((Knowledge) null, null);
        Assert.assertNull(know);
    }

    @Test
    public void testDto() {
        KnowledgeDto dto = new KnowledgeDto();
        dto.setValue("hallo");
        Knowledge know = KnowledgeConverter.convert(dto, new Person());
        Assert.assertEquals("hallo", know.getValue());
    }

    @Test
    public void testStatus() {
        Knowledge know = new Knowledge();
        know.setValue("hallo");
        KnowledgeDto dto = KnowledgeConverter.convert(know, new PersonDto());
        Assert.assertEquals("hallo", dto.getValue());
    }

    @Test
    public void testDtoListNull() {
        Collection<Knowledge> values = KnowledgeConverter.convertToDoList(null, new Person());
        Assert.assertNull(values);
    }

    @Test
    public void testDoListNull() {
        Collection<KnowledgeDto> values = KnowledgeConverter.convertToDtoList(null, new PersonDto());
        Assert.assertNull(values);
    }

    @Test
    public void testDtoList() {
        KnowledgeDto dto = new KnowledgeDto();
        dto.setValue("hallo");
        List<KnowledgeDto> list = new LinkedList<>();
        list.add(dto);
        Collection<Knowledge> values = KnowledgeConverter.convertToDoList(list, new Person());
        Assert.assertTrue(values instanceof List);
        List<Knowledge> valueList = (List<Knowledge>) values;
        Assert.assertEquals(1, valueList.size());
        Assert.assertEquals("hallo", valueList.get(0).getValue());
    }

    @Test
    public void testDoList() {
        Knowledge know = new Knowledge();
        know.setValue("hallo");
        List<Knowledge> list = new LinkedList<>();
        list.add(know);
        Collection<KnowledgeDto> values = KnowledgeConverter.convertToDtoList(list, new PersonDto());
        Assert.assertTrue(values instanceof List);
        List<KnowledgeDto> valueList = (List<KnowledgeDto>) values;
        Assert.assertEquals(1, valueList.size());
        Assert.assertEquals("hallo", valueList.get(0).getValue());
    }


}
