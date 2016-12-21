package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;
import java.util.List;

/**
 * Repository for the Person entity
 *
 * @author Kainz Patrick
 */
@Local
@Singleton
public class PersonRepositoryImpl
        extends AbstractRepository<Person>
        implements PersonRepository {

    public PersonRepositoryImpl() {
        super(Person.class);
    }

    @Override
    public Person findByUserId(long id) {
        final List<Person> results = entityManager.createNamedQuery("Person.findByUserId", Person.class)
                .setParameter("userid", id).getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

}
