package at.fhj.swd14.pse.contact;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dominik Groß
 */
@Entity
@Table(name = "contact")
@NamedQueries(@NamedQuery(name = "Contact.findByPersonID", query = "SELECT c FROM Contact c WHERE c.contactPK.person1Id=:personID OR c.contactPK.person2Id=:personID"))
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContactPK contactPK;

    public Contact() {
        // default public constructor
    }

    public Contact(long person1Id, long person2Id) {
        this.contactPK = new ContactPK(person1Id, person2Id);
    }

    public ContactPK getContactPK() {
        return contactPK;
    }

    public void setContactPK(ContactPK contactPK) {
        this.contactPK = contactPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactPK != null ? contactPK.hashCode() : 0);
        return hash;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (contactPK == null) {
			if (other.contactPK != null)
				return false;
		} else if (!contactPK.equals(other.contactPK))
			return false;
		return true;
	}
}
