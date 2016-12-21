package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.contact.Contact;
import at.fhj.swd14.pse.contact.ContactRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;
import java.util.List;

/**
 * Created by Dominik Groß on 11.11.2016.
 */
@Local
@Singleton
public class ContactRepositoryImpl
        extends AbstractRepository<Contact>
        implements ContactRepository {

    public ContactRepositoryImpl() {
        super(Contact.class);
    }

    @Override
    public List<Contact> findByPersonId(long id) {
        List<Contact> results = entityManager.createNamedQuery("Contact.findByPersonID", Contact.class)
                .setParameter("personID", id).getResultList();
        return results;
    }

}
