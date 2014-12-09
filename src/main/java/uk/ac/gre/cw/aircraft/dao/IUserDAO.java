package uk.ac.gre.cw.aircraft.dao;

import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Role;

import java.util.Collection;

public interface IUserDAO<User,Integer> extends IDAO<User,Integer> {

    public void updatePassword(User user) throws DAOException;

    public User findUserByUsernamePassword(String username, String password) throws DAOException;

    public Collection<Role> findUserRole(User user, boolean closeConn) throws DAOException;

    public Collection<Role> findUserRole(int userId, boolean closeConn) throws DAOException;

    public boolean mapRole(User user, Role role) throws DAOException;

    public boolean unmapRole(User user, Role role) throws DAOException;

    public boolean removeAllRoleMapping(User user) throws DAOException;
}
