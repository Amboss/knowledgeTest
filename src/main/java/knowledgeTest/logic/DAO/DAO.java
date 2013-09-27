package knowledgeTest.logic.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * Data Access Object (DAO) Interface
 */
public interface DAO<T> {

    public void save(T entity);

    public void update(T entity);

    public void delete(Serializable key);

    public T find(Serializable key);

    public List<T> findAll();

    public List<T> findAllByParam(String paramName, Object paramValue);
}
