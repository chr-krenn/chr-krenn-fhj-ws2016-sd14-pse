package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.repository.Repository;

/**
 * @author Richard Raumberger
 */
public interface PersonRepository extends Repository<Person> {

    /**
     * Returns a person object by its userid
     *
     * @param id userid to search for in the database
     * @return Person Object found, or null
     */
    Person findByUserId(long id);
}
