package uk.ac.gre.cw.aircraft.jdo.dao;

import org.datanucleus.store.query.QueryResult;
import uk.ac.gre.cw.aircraft.Common;
import uk.ac.gre.cw.aircraft.jdo.dao.exception.DAOException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.Collection;

/**
 * Created by Long Nguyen on 12/4/14.
 */
public abstract class AbstractDAO<T, V> implements IDAO<T, V> {
    private final ThreadLocal<PersistenceManager> pm = new ThreadLocal<PersistenceManager>();

    private final Class<T> targetClass;

    protected AbstractDAO(Class<T> targetClass) {
        if (targetClass == null) {
            throw new IllegalArgumentException("<Null>");
        }

        this.targetClass = targetClass;
    }

    protected synchronized PersistenceManager getPM() {
        PersistenceManager pm = this.pm.get();
        if ( pm == null ) {
            this.pm.set( pm = JDOHelper.getPersistenceManagerFactory(Common.PERSISTENCE_UNIT).getPersistenceManager() );
        }
        return pm;
    }

    protected <D> Collection<D> processQueryResults( Query query, Object... params  ) {
        Object result = query.execute(params);
        return (Collection<D>) result;
    }

    protected <D> D processQueryResult( Query query, Object... params  ) {
        Object result = query.executeWithArray(params);
        if ( result instanceof QueryResult ) {
            return (D) ((QueryResult) result).iterator().next();
        } else {
            return (D) result;
        }
    }

    @Override
    public Collection<T> findAll() {
        return (Collection<T>) getPM().newQuery(this.targetClass).execute();
    }

    @Override
    public T findOne(V keyValue) {
        return getPM().getObjectById( this.targetClass, keyValue );
    }

    @Override
    public T save(T object) throws DAOException {
        try {
            return getPM().makePersistent(object);
        } catch ( Throwable e ) {
            throw new DAOException( e.getMessage(), e );
        }
    }

    @Override
    public void delete(Long objectId) throws DAOException {
        T object = getPM().getObjectById( this.targetClass, objectId);
        if ( object == null ) {
            throw new DAOException("Requested object not exists");
        }

        getPM().deletePersistent(object);
    }

    @Override
    public void delete(T object) throws DAOException {
        try {
            getPM().deletePersistent(object);
        } catch ( Throwable e ) {
            throw new DAOException( e.getMessage(), e );
        }
    }
}
