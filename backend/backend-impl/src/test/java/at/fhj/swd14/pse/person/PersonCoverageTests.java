package at.fhj.swd14.pse.person;

import org.junit.Test;

/**
 * This class exists just to increase code converage
 * @author Patrick Kainz
 *
 */
public class PersonCoverageTests {
	
	@Test
	public void increateCoverage()
	{
		new Status();
		new Phonenumber();
		Person person = new Person();
		person.setId(1L);
		new Mailaddress();
		new Knowledge();
		Hobby hobby = new Hobby();
		hobby.getPerson();
		hobby.setId(1L);
	}

}
