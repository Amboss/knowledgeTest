package knowledgeTest.logic.DAO.support;

import knowledgeTest.logic.DAO.DAO;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    private SessionFactory getSessionFactory() {
        logger.debug("starting session");
        return sessionFactory;
    }

    /**
     * Save query
     * @param entity - object type to be handled
     * @throws RuntimeException
     */
    @Override
    public void save(T entity) {

        assert entity != null;
        logger.debug("initiating save query");
        try {
            getSessionFactory().getCurrentSession().save(entity);
        } catch (HibernateException e) {
            //e.printStackTrace();
            throw new RuntimeException("DAO Error: unable to initiate save query!");
        }
    }

    /**
     * Update query
     * @param entity - object type to be handled
     * @throws RuntimeException
     */
    @Override
    public void update(T entity) {

        assert entity != null;
        logger.debug("initiating update query");
        try {
            getSessionFactory().getCurrentSession().update(entity);
        } catch (HibernateException e) {
            //e.printStackTrace();
            throw new RuntimeException("DAO Error: unable to initiate update query!");
        }
    }

    /**
     * Delete query
     * @param key - object param (id)
     * @throws RuntimeException
     */
    @Override
    public void delete(Serializable key) {

        assert key != null;
        logger.debug("initiating delete query");
        Object entity = getSessionFactory().getCurrentSession().get(clazz, key);
        if (entity != null) {
            try {
                getSessionFactory().getCurrentSession().delete(entity);
            } catch (HibernateException e) {
                //e.printStackTrace();
                throw new RuntimeException("DAO Error: unable to initiate delete query!");
            }
        }
    }

    /**
     * Find query
     * @param key - object param (id)
     * @throws RuntimeException
     * Suppressed warnings of casting to generic type
     */
    @Override
    @SuppressWarnings("unchecked")
    public T find(Serializable key) {

        assert key != null;
        logger.debug("initiating find query");
        try {
            return (T) getSessionFactory().getCurrentSession().get(clazz, key);
        } catch (HibernateException e) {
            //e.printStackTrace();
            throw new RuntimeException("DAO Error: unable to initiate find query!");
        }
    }

    /**
     * Find all query
     * @throws RuntimeException
     * Suppressed warnings of casting to generic type
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {

        logger.debug("initiating find all query");
        try {
            return getSessionFactory().getCurrentSession().createCriteria(clazz).list();
        } catch (HibernateException e) {
            //e.printStackTrace();
            throw new RuntimeException("DAO Error: unable to initiate find all query!");
        }
    }

    /**
     * Find all by specified param query
     * @param paramName - name param
     * @param paramValue - param value
     * @throws RuntimeException
     * Suppressed warnings of casting to generic type
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAllByParam(final String paramName, final Object paramValue) {

        assert paramName != null || paramValue != null;
        logger.debug("initiating find all by param query");
        try {
            return getSessionFactory().getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq(paramName, paramValue)).list();
        } catch (HibernateException e) {
            //e.printStackTrace();
            throw new RuntimeException("DAO Error: unable to initiate find all by param query!");
        }
    }
}
