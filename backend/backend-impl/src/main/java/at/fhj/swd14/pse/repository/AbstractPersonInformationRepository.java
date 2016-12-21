package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.AbstractPersonInformation;

/**
 * @author Richard Raumberger
 */
public interface AbstractPersonInformationRepository<T extends AbstractPersonInformation>
        extends Repository<T> {

    /**
     * Finds an AbstractPersonInformationObject by its value and personid
     *
     * @param personid personid to search for in the database
     * @param value    value to search for in the database
     * @return AbstractPersonInformation Object found, or null
     */
    T findByValue(long personid, String value);
}
