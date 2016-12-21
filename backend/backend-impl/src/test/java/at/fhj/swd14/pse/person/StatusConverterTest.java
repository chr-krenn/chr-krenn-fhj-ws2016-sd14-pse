package at.fhj.swd14.pse.person;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StatusConverterTest {

    @Test
    public void testDtoNull() {
        Status status = StatusConverter.convert((StatusDto) null);
        Assert.assertNull(status);
    }

    @Test
    public void testStatusNull() {
        StatusDto status = StatusConverter.convert((Status) null);
        Assert.assertNull(status);
    }

    @Test
    public void testDto() {
        StatusDto dto = new StatusDto();
        dto.setName("hallo");
        Status status = StatusConverter.convert(dto);
        Assert.assertEquals("hallo", status.getName());
    }

    @Test
    public void testStatus() {
        Status status = new Status();
        status.setName("hallo");
        StatusDto dto = StatusConverter.convert(status);
        Assert.assertEquals("hallo", dto.getName());
    }

    @Test
    public void testDtoListNull() {
        Collection<Status> values = StatusConverter.convertToDoList(null);
        Assert.assertNull(values);
    }

    @Test
    public void testDoListNull() {
        Collection<StatusDto> values = StatusConverter.convertToDtoList(null);
        Assert.assertNull(values);
    }

    @Test
    public void testDtoList() {
        StatusDto dto = new StatusDto();
        dto.setName("hallo");
        List<StatusDto> list = new LinkedList<>();
        list.add(dto);
        Collection<Status> values = StatusConverter.convertToDoList(list);
        Assert.assertTrue(values instanceof List);
        List<Status> valueList = (List<Status>) values;
        Assert.assertEquals(1, valueList.size());
        Assert.assertEquals("hallo", valueList.get(0).getName());
    }

    @Test
    public void testDoList() {
        Status status = new Status();
        status.setName("hallo");
        List<Status> list = new LinkedList<>();
        list.add(status);
        Collection<StatusDto> values = StatusConverter.convertToDtoList(list);
        Assert.assertTrue(values instanceof List);
        List<StatusDto> valueList = (List<StatusDto>) values;
        Assert.assertEquals(1, valueList.size());
        Assert.assertEquals("hallo", valueList.get(0).getName());
    }


}
