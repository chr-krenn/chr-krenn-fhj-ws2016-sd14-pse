package at.fhj.swd14.pse.repository;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDtoTester;
import at.fhj.swd14.pse.person.PersonTestTools;

public class PersonRepositoryIntegrationTest extends AbstractRepositoryIDIntegrationTest<Person> {

	public PersonRepositoryIntegrationTest() {
		super(Person.class);
	}
	
	@Override
	protected long getDummyId(Person dummy) {
		return dummy.getId();
	}

	@Override
	protected AbstractRepository<Person> getRepository() {
		return new PersonRepository();
	}

	@Override
	protected Person createDummyEntity() {
		Person person = PersonTestTools.getDummyPerson();
		person.getDepartment().setId(null);
		manager.persist(person.getDepartment());
		manager.flush();
		person.getUser().setId(null);
		manager.persist(person.getUser());
		manager.flush();
		person.setId(null);
		person.getAdditionalMails().clear();
		person.getHobbies().clear();
		person.getKnowledges().clear();
		person.getNumbers().clear();
		return person;
	}

	@Override
	protected void assertEquals(Person expected, Person actual) {
		PersonDtoTester.assertEquals(PersonConverter.convert(expected), PersonConverter.convert(actual));
	}

	@Override
	protected Person modifyDummy(Person dummy) {
		dummy.setFirstname("afirstname");
		return dummy;
	}

	@Override
	protected void copyDummyPK(Person destination, Person source) {
		destination.setId(source.getId());
	}
	
	@Test
	public void testFindByUserId()
	{
		Person person = createDummyEntity();
		manager.persist(person);
		manager.flush();
		Person otherPerson = ((PersonRepository)repository).findByUserId(person.getUser().getId());
		Assert.assertNotNull(otherPerson);
		assertEquals(person, otherPerson);
	}

}
