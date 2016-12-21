package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.repository.Repository;

/**
 * @author Richard Raumberger
 */
public interface PersonImageRepository extends Repository<PersonImage> {

    /**
     * Returns a PersonImage by personid
     *
     * @param personid id of the person to search for in the database
     * @return PersonImage object found, or null
     */
    PersonImage getByPersonId(Long personid);
}
