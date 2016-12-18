package at.fhj.swd14.pse.repository;

import org.junit.Assert;

import at.fhj.swd14.pse.person.Status;

public class PersonStatusRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest<Status> {

	public PersonStatusRepositoryIntegrationTest() {
		super(Status.class);
	}
	
	@Override
	protected AbstractRepository<Status> getRepository() {
		return new PersonStatusRepository();
	}

	@Override
	protected Status createDummyEntity() {
		Status status = new Status();
		status.setName("test");
		return status;
	}

	@Override
	protected void assertEquals(Status expected, Status actual) {
		Assert.assertEquals(expected.getName(),actual.getName());
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
