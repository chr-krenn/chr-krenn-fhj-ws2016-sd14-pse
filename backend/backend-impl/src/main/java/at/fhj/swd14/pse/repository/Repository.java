package at.fhj.swd14.pse.repository;

import java.util.List;
import java.util.Map;

/**
 * @author Richard Raumberger
 */
public interface Repository<T> {

    T find(Long id);

    void update(T t);

    void save(T t);

    List<T> findAll();

    void refresh(T t);

    void remove(T t);

    void flush();

    List<T> executeNamedQuery(String name);

    List<T> executeNamedQuery(String name, Map<String, Object> parameter);

    int executeNativeQuery(String query);
}
