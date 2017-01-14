package at.fhj.swd14.pse.person;

import org.junit.Assert;
import org.junit.Test;
import at.fhj.swd14.pse.contact.Contact;
import at.fhj.swd14.pse.contact.ContactPK;

public class ContactTest {

	@Test
	public void testConstructor() {
		Contact contact = createContact();
		Assert.assertNotNull(contact.getContactPK());
	}
	
	@Test
	public void testContactPKSetter() {
		Contact contact = new Contact();
		contact.setContactPK(new ContactPK());
		Assert.assertNotNull(contact.getContactPK());
	}
	
	@Test
	public void testEquals() {
		Contact contact1 = createContact();
		Contact contact2 = createContact();
		Assert.assertEquals(contact1, contact2);
		Assert.assertEquals(contact1.hashCode(), contact2.hashCode());
	}
	
	@Test 
	public void testZeroHashCode() {
		Assert.assertEquals(new Contact().hashCode(), 0);
	}
	
	@Test
	public void testNotEqualsNull() {
		Assert.assertNotEquals(createContact(), null);
	}
	
	@Test
	public void testNotEqualsContactPKNull() {
		Contact contact1 = createContact();
		Contact contact2 = new Contact();
		Assert.assertNotEquals(contact1, contact2);
		Assert.assertNotEquals(contact2, contact1);
	}
	
	@Test
	public void testNotEqualsContactPK() {
		Contact contact1 = createContact();
		Contact contact2 = createContact();
		contact2.getContactPK().setPerson1Id(3);
		Assert.assertNotEquals(contact1, contact2);
	}
	
	@Test
	public void testEqualsSame() {
		Contact contact = createContact();
		Assert.assertEquals(contact, contact);
	}
	
	@Test
	public void testNotEqualsString() {
		Assert.assertNotEquals(createContact(), "");
	}
	
	@Test
	public void testEqualsBothContactPKNull() {
		Assert.assertEquals(new Contact(), new Contact());
	}
	
	private static Contact createContact() {
		return new Contact(1L, 2L);
	}
}
