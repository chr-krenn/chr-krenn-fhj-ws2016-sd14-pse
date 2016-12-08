package at.fhj.swd14.pse.person;

import org.junit.Test;

public class PersonTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNullUser()
	{
		Person person = new Person();
		person.setUser(null);
	}
	
}
