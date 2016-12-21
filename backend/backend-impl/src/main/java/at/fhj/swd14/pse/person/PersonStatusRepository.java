package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.repository.Repository;

/**
 * @author Richard Raumberger
 */
public interface PersonStatusRepository extends Repository<Status> {

    /**
     * Finds a Status object by its name
     *
     * @param name Name of the status to search for in the database
     * @return Status object found, or null
     */
    Status findByName(String name);
}
