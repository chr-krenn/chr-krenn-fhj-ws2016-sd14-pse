package at.fhj.swd14.pse.person;

import org.junit.Assert;

public class AbstractPersonInformationDtoTester {

	private AbstractPersonInformationDtoTester()
	{
		//gets rid of the default constructor
	}
	
    public static void assertEquals(AbstractPersonInformationDto expected, AbstractPersonInformationDto actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getValue(), actual.getValue());
    }

}
