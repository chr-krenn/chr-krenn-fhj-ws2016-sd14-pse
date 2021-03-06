package at.fhj.swd14.pse.person;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class HobbyConverterTest {

    @Test
    public void testDtoNull() {
        Hobby hobby = HobbyConverter.convert((HobbyDto) null, null);
        Assert.assertNull(hobby);
    }

    @Test
    public void testStatusNull() {
        HobbyDto hobby = HobbyConverter.convert((Hobby) null, null);
        Assert.assertNull(hobby);
    }

    @Test
    public void testDto() {
        HobbyDto dto = new HobbyDto();
        dto.setValue("hallo");
        Hobby hobby = HobbyConverter.convert(dto, new Person());
        Assert.assertEquals("hallo", hobby.getValue());
    }

    @Test
    public void testStatus() {
        Hobby hobby = new Hobby();
        hobby.setValue("hallo");
        HobbyDto dto = HobbyConverter.convert(hobby, new PersonDto());
        Assert.assertEquals("hallo", dto.getValue());
    }

    @Test
    public void testDtoListNull() {
        Collection<Hobby> values = HobbyConverter.convertToDoList(null, new Person());
        Assert.assertNull(values);
    }

    @Test
    public void testDoListNull() {
        Collection<HobbyDto> values = HobbyConverter.convertToDtoList(null, new PersonDto());
        Assert.assertNull(values);
    }

    @Test
    public void testDtoList() {
        HobbyDto dto = new HobbyDto();
        dto.setValue("hallo");
        List<HobbyDto> list = new LinkedList<>();
        list.add(dto);
        Collection<Hobby> values = HobbyConverter.convertToDoList(list, new Person());
        Assert.assertTrue(values instanceof List);
        List<Hobby> valueList = (List<Hobby>) values;
        Assert.assertEquals(1, valueList.size());
        Assert.assertEquals("hallo", valueList.get(0).getValue());
    }

    @Test
    public void testDoList() {
        Hobby hobby = new Hobby();
        hobby.setValue("hallo");
        List<Hobby> list = new LinkedList<>();
        list.add(hobby);
        Collection<HobbyDto> values = HobbyConverter.convertToDtoList(list, new PersonDto());
        Assert.assertTrue(values instanceof List);
        List<HobbyDto> valueList = (List<HobbyDto>) values;
        Assert.assertEquals(1, valueList.size());
        Assert.assertEquals("hallo", valueList.get(0).getValue());
    }


}
