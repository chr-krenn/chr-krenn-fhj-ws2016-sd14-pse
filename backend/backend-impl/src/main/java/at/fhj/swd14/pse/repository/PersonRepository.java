package at.fhj.swd14.pse.repository;

import javax.ejb.Stateless;

import at.fhj.swd14.pse.person.Person;

/**
 * Repository for the Person entity
 * @author Kainz Patrick
 *
 */
@Stateless
public class PersonRepository extends AbstractRepository<Person> {

	public PersonRepository()
	{
		super(Person.class);
	}
	
}
