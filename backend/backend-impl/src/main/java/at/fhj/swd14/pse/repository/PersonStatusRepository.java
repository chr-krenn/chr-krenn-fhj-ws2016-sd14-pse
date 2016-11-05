package at.fhj.swd14.pse.repository;

import javax.ejb.Stateless;

import at.fhj.swd14.pse.person.Status;

/**
 * Repository for person status
 * @author Kainz Patrick
 *
 */
@Stateless
public class PersonStatusRepository extends AbstractRepository<Status> {

	public PersonStatusRepository()
	{
		super(Status.class);
	}
	
}
