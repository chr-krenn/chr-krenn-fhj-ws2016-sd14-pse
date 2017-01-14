package at.fhj.swd14.pse.person;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.contact.ContactPK;

public class ContactPKTest {

	@Test
	public void testDefaultConstructor() {
		ContactPK contactPK = new ContactPK();
		contactPK.setPerson1Id(1L);
		contactPK.setPerson2Id(2L);
		assertContactPK(contactPK);
	}
	
	@Test
	public void testConstructor() {
		assertContactPK(createContactPK());
	}
	
	@Test
	public void testEquals() {
		ContactPK contactPK1 = createContactPK();
		ContactPK contactPK2 = createContactPK();
		Assert.assertEquals(contactPK1, contactPK2);
		Assert.assertEquals(contactPK1.hashCode(), contactPK2.hashCode());
	}
	
	@Test
	public void testNotEqualsNull() {
		Assert.assertNotEquals(createContactPK(), null);
	}
	
	@Test
	public void testEqualsWrongId1() {
		ContactPK contactPK2 = createContactPK();
		contactPK2.setPerson1Id(3);
		Assert.assertNotEquals(createContactPK(), contactPK2);
	}
	
	@Test
	public void testEqualsWrongId2() {
		ContactPK contactPK2 = createContactPK();
		contactPK2.setPerson2Id(3);
		Assert.assertNotEquals(createContactPK(), contactPK2);
	}
	
	private static ContactPK createContactPK() {
		return new ContactPK(1L, 2L);
	}

	private void assertContactPK(ContactPK contactPK) {
		Assert.assertEquals(contactPK.getPerson1Id(), 1L);
		Assert.assertEquals(contactPK.getPerson2Id(), 2L);
	}
}
