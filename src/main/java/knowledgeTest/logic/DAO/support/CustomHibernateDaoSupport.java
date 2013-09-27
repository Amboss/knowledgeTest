package knowledgeTest.logic.DAO.support;

import knowledgeTest.logic.DAO.DAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Custom Hibernate Data Access Object Support with sessionFactory instead HibernateDaoSupport
 */
@Transactional
public class CustomHibernateDaoSupport<T> implements DAO<T> {

    protected static Logger logger = Logger.getLogger("CustomHibernateDaoSupport");

    private Class<T> clazz;

    private SessionFactory sessionFactory;

    public CustomHibernateDaoSupport(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Initiation of session factory
     * @param sessionFactory handled by spring configuration
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory  getSessionFactory() {
        logger.debug("starting session");
        return sessionFactory;
    }

    /**
     * Save query
     * @param entity - object type to be handled
     */
    @Override
    public void save(T entity) {
        logger.debug("initiating save query");
        getSessionFactory().getCurrentSession().save(entity);
    }

    /**
     * Update query
     * @param entity - object type to be handled
     */
    @Override
    public void update(T entity) {
        logger.debug("initiating update query");
        getSessionFactory().getCurrentSession().update(entity);
    }

    /**
     * Delete query
     * @param key - object parameter
     */
    @Override
    public void delete(Serializable key) {
        logger.debug("initiating delete query");
        Object entity = getSessionFactory().getCurrentSession().get(clazz, key);
        if (entity != null) {
            getSessionFactory().getCurrentSession().delete(entity);
        }
    }

    /**
     * Find query
     * @param key - object parameter
     */
    @Override
    @SuppressWarnings("unchecked")
    public T find(Serializable key) {
        logger.debug("initiating find query");
        return (T) getSessionFactory().getCurrentSession().get(clazz, key);
    }

    /**
     * Find all query
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        logger.debug("initiating find all query");
        return getSessionFactory().getCurrentSession().createCriteria(clazz).list();
    }

    /**
     * Find all by specified param query
     * @param paramName - name param
     * @param paramValue - param value
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAllByParam(final String paramName, final Object paramValue) {
        logger.debug("initiating find all by param query");
        return getSessionFactory().getCurrentSession().createCriteria(clazz)
                .add(Restrictions.eq(paramName, paramValue))
                .list();
    }
}
