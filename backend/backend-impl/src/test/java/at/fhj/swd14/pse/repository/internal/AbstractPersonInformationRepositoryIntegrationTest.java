package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.AbstractPersonInformation;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonTestTools;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractPersonInformationRepositoryIntegrationTest<T extends AbstractPersonInformation> extends AbstractRepositoryIDIntegrationTest<T> {

    public AbstractPersonInformationRepositoryIntegrationTest(Class<T> clazz) {
        super(clazz);
    }


    protected abstract AbstractPersonInformationRepositoryImpl<T> getInfoRepository();

    @Override
    protected AbstractRepository<T> getRepository() {
        return getInfoRepository();
    }


    @Override
    protected long getDummyId(T dummy) {
        return dummy.getId();
    }


    protected abstract T createDummyEntity(Person person);

    @Override
    protected T createDummyEntity() {
        Person personDummy = PersonTestTools.getDummyPerson();
        personDummy.getAdditionalMails().clear();
        personDummy.getHobbies().clear();
        personDummy.getKnowledges().clear();
        personDummy.getNumbers().clear();
        personDummy.setId(null);
        manager.persist(personDummy);
        manager.flush();
        return createDummyEntity(personDummy);
    }

    @Override
    protected void assertEquals(T expected, T actual) {
        Assert.assertEquals(expected.getPerson().getId(), expected.getPerson().getId());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getValue(), actual.getValue());
    }

    @Override
    protected T modifyDummy(T dummy) {
        dummy.setValue(dummy.getValue() + "1");
        return dummy;
    }

    @Override
    protected void copyDummyPK(T destination, T source) {
        destination.setId(source.getId());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testFindByValue() {
        T dummy = createDummyEntity();
        manager.persist(dummy);
        manager.flush();
        AbstractPersonInformationRepositoryImpl<T> repo = (AbstractPersonInformationRepositoryImpl<T>) repository;
        T otherDummy = repo.findByValue(dummy.getPerson().getId(), dummy.getValue());
        Assert.assertNotNull(otherDummy);
        assertEquals(dummy, otherDummy);
    }

}
