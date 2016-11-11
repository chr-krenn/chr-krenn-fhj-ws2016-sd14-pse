package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.contact.Contact;
import at.fhj.swd14.pse.person.Person;

import javax.ejb.Local;
import javax.ejb.Singleton;
import java.util.List;

/**
 * Created by Dominik Groß on 11.11.2016.
 */
@Local
@Singleton
public class ContactRepository extends AbstractRepository<Contact> {

    public ContactRepository()
    {
        super(Contact.class);
    }

    public List<Contact> findByPersonId(long id)
    {
        List<Contact> results =  entityManager.createNamedQuery("Contact.findByPersonID",Contact.class)
                .setParameter("personID", id).getResultList();
        return results;
    }

}
