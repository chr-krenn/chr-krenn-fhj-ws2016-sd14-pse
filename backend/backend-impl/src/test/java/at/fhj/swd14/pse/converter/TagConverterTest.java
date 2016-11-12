package at.fhj.swd14.pse.converter;


import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.tag.TagDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

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
}
