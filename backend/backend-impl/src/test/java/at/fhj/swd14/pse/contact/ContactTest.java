package at.fhj.swd14.pse.contact;

import org.junit.Assert;
import org.junit.Test;

public class ContactTest {

	@Test
	public void testConstructor() {
		Contact contact = ContactTestTools.createContact();
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
		Contact contact1 = ContactTestTools.createContact();
		Contact contact2 = ContactTestTools.createContact();
		Assert.assertEquals(contact1, contact2);
		Assert.assertEquals(contact1.hashCode(), contact2.hashCode());
	}
	
	@Test 
	public void testZeroHashCode() {
		Assert.assertEquals(new Contact().hashCode(), 0);
	}
	
	@Test
	public void testNotEqualsNull() {
		Assert.assertNotEquals(ContactTestTools.createContact(), null);
	}
	
	@Test
	public void testNotEqualsContactPKNull() {
		Contact contact1 = ContactTestTools.createContact();
		Contact contact2 = new Contact();
		Assert.assertNotEquals(contact1, contact2);
		Assert.assertNotEquals(contact2, contact1);
	}
	
	@Test
	public void testNotEqualsContactPK() {
		Contact contact1 = ContactTestTools.createContact();
		Contact contact2 = ContactTestTools.createContact();
		contact2.getContactPK().setPerson1Id(3);
		Assert.assertNotEquals(contact1, contact2);
	}
	
	@Test
	public void testEqualsSame() {
		Contact contact = ContactTestTools.createContact();
		Assert.assertEquals(contact, contact);
	}
	
	@Test
	public void testNotEqualsString() {
		Assert.assertNotEquals(ContactTestTools.createContact(), "");
	}
	
	@Test
	public void testEqualsBothContactPKNull() {
		Assert.assertEquals(new Contact(), new Contact());
	}
}
