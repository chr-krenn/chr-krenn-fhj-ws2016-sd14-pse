package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.person.Status;

/**
 * Repository for person status
 * @author Kainz Patrick
 *
 */
@Local
@Singleton
public class PersonStatusRepository extends AbstractRepository<Status> {

	public PersonStatusRepository()
	{
		super(Status.class);
	}
	
	/**
	 * Finds a Status object by its name
	 * @param name Name of the status to search for in the database
	 * @return Status object found, or null
	 */
	public Status findByName(String name)
	{
		return entityManager.createNamedQuery("PersonStatus.findByName",Status.class).setParameter("status_name", name).getSingleResult();
	}
	
}
