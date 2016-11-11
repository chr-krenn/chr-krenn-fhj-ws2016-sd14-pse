package at.fhj.swd14.pse.repository;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T> {

    protected final Class<T> entityClass;

    @PersistenceContext(unitName = "SEP")
    protected EntityManager entityManager;

    protected AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T find(Long id) {
        return entityManager.find(entityClass, id);
    }

    public void update(T t)
    {
    	entityManager.merge(t);
    }
    
    public void save(T t) {
    	entityManager.persist(t);
    }

    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery(
                "SELECT entity FROM " + entityClass.getTypeName() + " entity" + " ORDER BY entity.id", entityClass);

        return query.getResultList();
    }

    public void refresh(T t) {
        entityManager.refresh(t);
    }

    public void remove(T t) {
        entityManager.remove(t);
    }

    public void flush()
    {
    	entityManager.flush();
    }
    
    public List<T> executeNamedQuery(String name) {
        TypedQuery<T> query = entityManager.createNamedQuery(name, entityClass);
        return query.getResultList();
    }

    public List<T> executeNamedQuery(String name, Map<String, Object> parameter) {
        TypedQuery<T> query = entityManager.createNamedQuery(name, entityClass);
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    public int executeNativeQuery(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        return nativeQuery.executeUpdate();
    }
}
