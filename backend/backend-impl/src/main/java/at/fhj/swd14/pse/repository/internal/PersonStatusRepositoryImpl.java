package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.PersonStatusRepository;
import at.fhj.swd14.pse.person.Status;

import javax.ejb.Local;
import javax.ejb.Singleton;

/**
 * Repository for person status
 *
 * @author Kainz Patrick
 */
@Local
@Singleton
public class PersonStatusRepositoryImpl
        extends AbstractRepository<Status>
        implements PersonStatusRepository {

    public PersonStatusRepositoryImpl() {
        super(Status.class);
    }

    @Override
    public Status findByName(String name) {
        return entityManager.createNamedQuery("PersonStatus.findByName", Status.class).setParameter("status_name", name).getSingleResult();
    }

}
