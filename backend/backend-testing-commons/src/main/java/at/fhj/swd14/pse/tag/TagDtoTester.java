package at.fhj.swd14.pse.tag;

import org.junit.Assert;

import java.util.List;

public class TagDtoTester {

    private TagDtoTester() {
        //hide the constructor
    }

    public static void assertEquals(TagDto expected, TagDto actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    public static void assertEquals(List<TagDto> expected, List<TagDto> actual) {

        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++)
            assertEquals(expected.get(i), actual.get(i));
    }
}
