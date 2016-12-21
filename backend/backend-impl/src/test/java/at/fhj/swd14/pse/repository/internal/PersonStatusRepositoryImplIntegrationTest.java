package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Status;
import org.junit.Assert;

public class PersonStatusRepositoryImplIntegrationTest extends AbstractRepositoryIntegrationTest<Status> {

    public PersonStatusRepositoryImplIntegrationTest() {
        super(Status.class);
    }

    @Override
    protected AbstractRepository<Status> getRepository() {
        return new PersonStatusRepositoryImpl();
    }

    @Override
    protected Status createDummyEntity() {
        Status status = new Status();
        status.setName("test");
        return status;
    }

    @Override
    protected void assertEquals(Status expected, Status actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    @Override
    protected Status fetchDummy(Status dummy) {
        return manager.find(Status.class, dummy.getName());
    }

    @Override
    protected Status modifyDummy(Status dummy) {
        //can only modify the primary key so don't do anything here, this renders the test useless but still
        return dummy;
    }

    @Override
    protected void copyDummyPK(Status destination, Status source) {
        destination.setName(source.getName());
    }

}
