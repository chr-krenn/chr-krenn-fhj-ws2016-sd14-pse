package at.fhj.swd14.pse.contact;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Dominik Groß
 */
@Entity
@Table(name = "contact")
@NamedQueries({
        @NamedQuery(name = "Contact.findByPersonID", query = "SELECT c FROM Contact c WHERE c.contactPK.person1Id=:personID OR c.contactPK.person2Id=:personID")})
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContactPK contactPK;

    public Contact() {
    }

    public Contact(ContactPK contactPK) {
        this.contactPK = contactPK;
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
    public boolean equals(Object object) {
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.contactPK == null && other.contactPK != null) || (this.contactPK != null && !this.contactPK.equals(other.contactPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "producer_consumer.Contact[ contactPK=" + contactPK + " ]";
    }

}
