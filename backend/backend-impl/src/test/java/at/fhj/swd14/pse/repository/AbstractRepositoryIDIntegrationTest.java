package at.fhj.swd14.pse.repository;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractRepositoryIDIntegrationTest<T> extends AbstractRepositoryIntegrationTest<T> {

    protected abstract long getDummyId(T dummy);

    public AbstractRepositoryIDIntegrationTest(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected T fetchDummy(T dummy) {
        return manager.find(clazz, getDummyId(dummy));
    }

    @Test
    public void testFind() {
        T dummy = createDummyEntity();
        manager.persist(dummy);
        manager.flush();
        T actual = repository.find(getDummyId(dummy));
        Assert.assertNotNull(actual);
        assertEquals(dummy, actual);
    }

}
