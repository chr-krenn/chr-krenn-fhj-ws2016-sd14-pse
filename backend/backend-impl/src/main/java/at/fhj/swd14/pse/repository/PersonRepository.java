package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

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
	
	public Person findByUserId(long id)
	{
		return entityManager.createNamedQuery("Person.findByUserId",Person.class)
				.setParameter("userid", id).getSingleResult();
	}
	
}
