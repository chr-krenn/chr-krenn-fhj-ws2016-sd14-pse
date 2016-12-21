package at.fhj.swd14.pse.contact;

import at.fhj.swd14.pse.repository.Repository;

import java.util.List;

/**
 * @author Richard Raumberger
 */
public interface ContactRepository extends Repository<Contact> {
    List<Contact> findByPersonId(long id);
}
