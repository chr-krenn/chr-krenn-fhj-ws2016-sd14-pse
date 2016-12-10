package at.fhj.swd14.pse.repository;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractRepositoryDatabaseIDTest<T> extends AbstractRepositoryDatabaseTest<T>{

	protected abstract long getDummyId(T dummy);
	
	public AbstractRepositoryDatabaseIDTest(Class<T> clazz) {
		super(clazz);
	}

	@Override
	protected T fetchDummy(T dummy) {
		return manager.find(clazz, getDummyId(dummy));
	}

	@Test
	public void testFind()
	{
		T dummy = createDummyEntity();
		manager.persist(dummy);
		manager.flush();
		T actual = getRepository().find(getDummyId(dummy));
		Assert.assertNotNull(actual);
		assertEquals(dummy,actual);
	}
	
}
