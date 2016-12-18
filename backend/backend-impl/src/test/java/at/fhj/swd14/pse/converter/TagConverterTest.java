package at.fhj.swd14.pse.converter;


import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.tag.TagDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TagConverterTest {

    @Test
    public void testConvertToDto(){

        Tag t = new Tag(10L, "testTag");
        TagDto dto = TagConverter.convert(t);
        assertEquals(t.getId(), dto.getId());
        assertEquals(t.getName(), dto.getName());
    }

    @Test
    public void testConvertToEntity(){

        TagDto dto = new TagDto(12L, "testEntity");
        Tag t = TagConverter.convert(dto);
        assertEquals(t.getId(), dto.getId());
        assertEquals(t.getName(), dto.getName());
    }

    @Test
    public void testDtoList()
    {
        TagDto tagDto1 = new TagDto(20L, "testTag01");
        TagDto tagDto2 = new TagDto(21L, "testTag02");
        List<TagDto> list = new ArrayList<>();
        list.add(tagDto1);
        list.add(tagDto2);
        Collection<Tag> values = TagConverter.convertToList(list);
        Assert.assertTrue(values instanceof List);
        List<Tag> valueList = (List<Tag>)values;
        Assert.assertEquals(2, valueList.size());
        Assert.assertEquals("testTag01", valueList.get(0).getName());
        Assert.assertEquals("testTag02", valueList.get(1).getName());
    }

    @Test
    public void testEntityList()
    {
        Tag tag1 = new Tag(20L, "testTag01");
        Tag tag2 = new Tag(21L, "testTag02");
        List<Tag> list = new ArrayList<>();
        list.add(tag1);
        list.add(tag2);
        Collection<TagDto> values = TagConverter.convertToDtoList(list);
        Assert.assertTrue(values instanceof List);
        List<TagDto> valueList = (List<TagDto>)values;
        Assert.assertEquals(2, valueList.size());
        Assert.assertEquals("testTag01", valueList.get(0).getName());
        Assert.assertEquals("testTag02", valueList.get(1).getName());
    }

    @Test
    public void testConvertNullDto(){

        Tag tag = TagConverter.convert((TagDto)null);
        Assert.assertNull(tag);
    }

    @Test
    public void testConvertNullEntity(){

        TagDto tagDto = TagConverter.convert((Tag)null);
        Assert.assertNull(tagDto);
    }

    @Test
    public void testConvertNullList(){

        Collection<TagDto> dtoList = TagConverter.convertToDtoList(null);
        Assert.assertTrue(dtoList.isEmpty());
    }

    @Test
    public void testConvertNullDtoList(){

        Collection<Tag> tagList = TagConverter.convertToList(null);
        Assert.assertTrue(tagList.isEmpty());
    }
}
