package at.fhj.swd14.pse.tag;

import org.junit.Assert;

public class TagDtoTester {

	public static void assertEquals(TagDto expected, TagDto actual) {
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getName(), actual.getName());
	}


}
