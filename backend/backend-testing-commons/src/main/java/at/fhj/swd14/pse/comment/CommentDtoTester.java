package at.fhj.swd14.pse.comment;

import org.junit.Assert;

public class CommentDtoTester
{
	public static void assertEquals(CommentDto expected, CommentDto actual)
	{
		Assert.assertEquals(expected.getAuthor(), actual.getAuthor());
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getParentMessage(), actual.getParentMessage());
		Assert.assertEquals(expected.getText(), actual.getText());
	}
}
