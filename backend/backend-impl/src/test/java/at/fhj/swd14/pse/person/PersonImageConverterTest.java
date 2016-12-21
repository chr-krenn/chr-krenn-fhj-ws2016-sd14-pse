package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PersonImageConverterTest {

    @Test
    public void testDtoNull() {
        PersonImage img = PersonImageConverter.convert((PersonImageDto) null);
        Assert.assertNull(img);
    }

    @Test
    public void testPersonImageNull() {
        PersonImageDto img = PersonImageConverter.convert((PersonImage) null);
        Assert.assertNull(img);
    }

    @Test
    public void testDto() {
        PersonImageDto dto = new PersonImageDto(1L);
        dto.setContentType("png");
        byte[] data = new byte[1];
        dto.setData(data);
        dto.setId(1L);
        PersonDto person = new PersonDto(1L);
        person.setUser(new UserDto());
        dto.setPerson(person);
        PersonImage img = PersonImageConverter.convert(dto);
        Assert.assertEquals((Long) 1L, img.getId());
        Assert.assertEquals("png", img.getContentType());
        Assert.assertEquals(data, img.getData());
        Assert.assertEquals((Long) 1L, img.getPerson().getId());
    }

    @Test
    public void testPersonImage() {
        PersonImage img = new PersonImage(1L);
        img.setContentType("png");
        byte[] data = new byte[1];
        img.setData(data);
        img.setId(1L);
        img.setPerson(new Person(1L, new User()));
        PersonImageDto dto = PersonImageConverter.convert(img);
        Assert.assertEquals((Long) 1L, dto.getId());
        Assert.assertEquals("png", dto.getContentType());
        Assert.assertEquals(data, dto.getData());
        Assert.assertEquals((Long) 1L, dto.getPerson().getId());
    }

    @Test
    public void testDtoListNull() {
        Collection<PersonImage> values = PersonImageConverter.convertToDoList(null);
        Assert.assertNull(values);
    }

    @Test
    public void testDoListNull() {
        Collection<PersonImageDto> values = PersonImageConverter.convertToDtoList(null);
        Assert.assertNull(values);
    }

    @Test
    public void testDtoList() {
        PersonImageDto dto = new PersonImageDto(1L);
        dto.setContentType("png");
        byte[] data = new byte[1];
        dto.setData(data);
        dto.setId(1L);
        PersonDto person = new PersonDto(1L);
        person.setUser(new UserDto());
        dto.setPerson(person);
        List<PersonImageDto> list = new LinkedList<>();
        list.add(dto);
        Collection<PersonImage> values = PersonImageConverter.convertToDoList(list);
        Assert.assertTrue(values instanceof List);
        List<PersonImage> valueList = (List<PersonImage>) values;
        Assert.assertEquals(1, valueList.size());
        Assert.assertEquals((Long) 1L, valueList.get(0).getId());
        Assert.assertEquals("png", valueList.get(0).getContentType());
        Assert.assertEquals(data, valueList.get(0).getData());
        Assert.assertEquals((Long) 1L, valueList.get(0).getPerson().getId());
    }

    @Test
    public void testDoList() {
        PersonImage img = new PersonImage(1L);
        img.setContentType("png");
        byte[] data = new byte[1];
        img.setData(data);
        img.setId(1L);
        img.setPerson(new Person(1L, new User()));
        List<PersonImage> list = new LinkedList<>();
        list.add(img);
        Collection<PersonImageDto> values = PersonImageConverter.convertToDtoList(list);
        Assert.assertTrue(values instanceof List);
        List<PersonImageDto> valueList = (List<PersonImageDto>) values;
        Assert.assertEquals(1, valueList.size());
        Assert.assertEquals((Long) 1L, valueList.get(0).getId());
        Assert.assertEquals("png", valueList.get(0).getContentType());
        Assert.assertEquals(data, valueList.get(0).getData());
        Assert.assertEquals((Long) 1L, valueList.get(0).getPerson().getId());
    }


}
