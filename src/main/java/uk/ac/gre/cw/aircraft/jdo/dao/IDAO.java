package uk.ac.gre.cw.aircraft.jdo.dao;

import uk.ac.gre.cw.aircraft.jdo.dao.exception.DAOException;

import java.util.Collection;

/**
 * Created by Long Nguyen on 12/4/14.
 */
public interface IDAO<T, V> {

    public Collection<T> findAll();

    public T findOne( V keyValue );

    public T save( T object ) throws DAOException;

    public void delete( T object ) throws DAOException;

    public void delete( Long objectId ) throws DAOException;

}
