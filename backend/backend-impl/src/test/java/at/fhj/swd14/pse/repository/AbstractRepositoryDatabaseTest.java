package at.fhj.swd14.pse.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractRepositoryDatabaseTest<T> {
	
	protected Class<T> clazz;
	protected EntityTransaction transaction;
	protected EntityManager manager;
	
	public AbstractRepositoryDatabaseTest(Class<T> clazz)
	{
		this.clazz = clazz;
	}
	
	protected abstract AbstractRepository<T> getRepository();
	protected abstract T createDummyEntity();
	protected abstract void assertEquals(T expected, T actual);
	protected abstract T fetchDummy(T dummy);
	protected abstract T modifyDummy(T dummy);
	protected abstract void copyDummyPK(T destination,T source);
	
	private void exec(String query)
	{
		manager.createQuery(query).executeUpdate();
	}
	
	//@Before
	public void setup() throws ClassNotFoundException
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SEP_TEST");
		manager = factory.createEntityManager();
		getRepository().entityManager=manager;
		transaction = manager.getTransaction();
		transaction.begin();
		exec("DELETE FROM "+clazz.getTypeName());
	}
	
	//@Test
	public void testUpdate()
	{
		T dummy = createDummyEntity();
		manager.persist(dummy);
		manager.flush();
		T anotherDummy = createDummyEntity();
		anotherDummy = modifyDummy(anotherDummy);
		copyDummyPK(anotherDummy, dummy);
		getRepository().update(anotherDummy);
		manager.flush();
		T result = fetchDummy(dummy);
		assertEquals(anotherDummy,result);
	}
	
	//@Test
	public void testSave()
	{
		T dummy = createDummyEntity();
		getRepository().save(dummy);
		manager.flush();
		T result = fetchDummy(dummy);
		assertEquals(dummy,result);
	}
	
	//@Test
	public void testFindAll()
	{
		T dummy = createDummyEntity();
		manager.persist(dummy);
		manager.flush();
		List<T> allDummys = getRepository().findAll();
		Assert.assertEquals(1, allDummys.size());
		assertEquals(dummy,allDummys.get(0));
	}
	
	//@Test 
	public void testRefresh()
	{
		T dummy = createDummyEntity();
		manager.persist(dummy);
		manager.flush();
		T anotherDummy = createDummyEntity();
		anotherDummy = modifyDummy(anotherDummy);
		copyDummyPK(anotherDummy,dummy);
		manager.merge(anotherDummy);
		manager.flush();
		manager.refresh(dummy);
		assertEquals(dummy,anotherDummy);
	}
	
	//@Test
	public void testRemove()
	{
		T dummy = createDummyEntity();
		manager.persist(dummy);
		manager.flush();
		getRepository().remove(dummy);
		manager.flush();
		T anotherDummy = fetchDummy(dummy);
		Assert.assertNull(anotherDummy);
	}
	
	//@After
	public void teardown()
	{
		transaction.rollback();
	}

}
