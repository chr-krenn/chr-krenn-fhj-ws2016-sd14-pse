package at.fhj.swd14.pse.person;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.person.tools.PersonComparator;

public class PersonComparatorTest {

	@Test
	public void testCompare() {
		PersonDto personDto = new PersonDto();
		personDto.setLastname("test");
		int compare = new PersonComparator().compare(personDto, personDto);
		Assert.assertEquals(compare, 0);
	}
}
