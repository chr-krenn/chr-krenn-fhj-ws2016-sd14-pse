package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.PersonImage;
import at.fhj.swd14.pse.person.PersonImageRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;
import java.util.List;

/**
 * Repository for person image
 *
 * @author Patrick Kainz
 */
@Local
@Singleton
public class PersonImageRepositoryImpl
        extends AbstractRepository<PersonImage>
        implements PersonImageRepository {

    public PersonImageRepositoryImpl() {
        super(PersonImage.class);
    }

    @Override
    public PersonImage getByPersonId(Long personid) {
        final List<PersonImage> results = entityManager.createNamedQuery("PersonImage.findByPerson", PersonImage.class)
                .setParameter("personid", personid).getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

}
