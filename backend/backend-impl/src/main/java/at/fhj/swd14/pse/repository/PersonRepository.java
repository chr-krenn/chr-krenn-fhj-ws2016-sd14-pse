package at.fhj.swd14.pse.repository;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.person.Person;

/**
 * Repository for the Person entity
 * @author Kainz Patrick
 *
 */
@Local
@Singleton
public class PersonRepository extends AbstractRepository<Person> {

	public PersonRepository()
	{
		super(Person.class);
	}
	
	/**
	 * Returns a person object by its userid
	 * @param id userid to search for in the database
	 * @return Person Object found, or null
	 */
	public Person findByUserId(long id)
	{
		List<Person> results =  entityManager.createNamedQuery("Person.findByUserId",Person.class)
				.setParameter("userid", id).getResultList();
		if(results.isEmpty())
			return null;
		else
			return results.get(0);
	}
	
}
