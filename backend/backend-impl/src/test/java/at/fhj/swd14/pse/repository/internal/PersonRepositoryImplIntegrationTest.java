package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonAssert;
import at.fhj.swd14.pse.person.PersonConverter;
import at.fhj.swd14.pse.person.PersonTestTools;
import org.junit.Assert;
import org.junit.Test;

public class PersonRepositoryImplIntegrationTest extends AbstractRepositoryIDIntegrationTest<Person> {

    public PersonRepositoryImplIntegrationTest() {
        super(Person.class);
    }

    @Override
    protected long getDummyId(Person dummy) {
        return dummy.getId();
    }

    @Override
    protected AbstractRepository<Person> getRepository() {
        return new PersonRepositoryImpl();
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
        PersonAssert.assertEquals(PersonConverter.convert(expected), PersonConverter.convert(actual));
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
    public void testFindByUserId() {
        Person person = createDummyEntity();
        manager.persist(person);
        manager.flush();
        Person otherPerson = ((PersonRepositoryImpl) repository).findByUserId(person.getUser().getId());
        Assert.assertNotNull(otherPerson);
        assertEquals(person, otherPerson);
    }

}
