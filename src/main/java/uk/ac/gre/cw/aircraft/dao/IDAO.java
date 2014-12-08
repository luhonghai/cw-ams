package uk.ac.gre.cw.aircraft.dao;

import uk.ac.gre.cw.aircraft.dao.exception.DAOException;

import java.util.Collection;

public interface IDAO<T, V> {

    public Collection<T> findAll() throws DAOException;

    public T findOne( V keyValue ) throws DAOException;

    public T create( T object ) throws DAOException;

    public T update( T object ) throws DAOException;

    public void delete( T object ) throws DAOException;

    public void delete( Integer objectId ) throws DAOException;

}
