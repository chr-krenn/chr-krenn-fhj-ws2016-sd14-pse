package at.fhj.swd14.pse.contact;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Dominik Groß
 */
@Embeddable
public class ContactPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "person1_id")
    private long person1Id;
    @Basic(optional = false)
    @Column(name = "person2_id")
    private long person2Id;

    public ContactPK() {
    }

    public ContactPK(long person1Id, long person2Id) {
        this.person1Id = person1Id;
        this.person2Id = person2Id;
    }

    public long getPerson1Id() {
        return person1Id;
    }

    public void setPerson1Id(long person1Id) {
        this.person1Id = person1Id;
    }

    public long getPerson2Id() {
        return person2Id;
    }

    public void setPerson2Id(long person2Id) {
        this.person2Id = person2Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) person1Id;
        hash += (int) person2Id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContactPK)) {
            return false;
        }
        ContactPK other = (ContactPK) object;
        if (this.person1Id != other.person1Id) {
            return false;
        }
        return this.person2Id == other.person2Id;
    }

    @Override
    public String toString() {
        return "producer_consumer.ContactPK[ person1Id=" + person1Id + ", person2Id=" + person2Id + " ]";
    }

}
