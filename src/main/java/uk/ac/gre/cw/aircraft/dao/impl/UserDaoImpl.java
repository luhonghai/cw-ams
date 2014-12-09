package uk.ac.gre.cw.aircraft.dao.impl;

import uk.ac.gre.cw.aircraft.dao.AbstractDAO;
import uk.ac.gre.cw.aircraft.dao.DatabaseConnector;
import uk.ac.gre.cw.aircraft.dao.IUserDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Role;
import uk.ac.gre.cw.aircraft.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class UserDaoImpl extends AbstractDAO<User,Integer> implements IUserDAO<User,Integer> {

    @Override
    public Collection<User> findAll() throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("find_all_users"));
            rs = statement.executeQuery();
            if (rs.next()) {
                Collection<User> users = new ArrayList<User>();
                do {
                    users.add(resultSetToUser(rs, false));
                } while (rs.next());
                return users;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find all user" ,e);
        } finally {
            close();
        }
        return null;
    }

    protected User resultSetToUser(ResultSet rs, boolean includePassword) throws SQLException, DAOException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setGender(rs.getBoolean("gender"));
        user.setUsername(rs.getString("username"));
        if (includePassword)
            user.setPassword(rs.getString("password"));
        user.setCreatedDate(getDate(rs, "created_date"));
        user.setRoles(findUserRole(user, false));
        user.initAdditionalData();
        return user;
    }

    @Override
    public User findOne(Integer keyValue) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("find_one_user_by_id"));
            statement.setInt(1, keyValue);
            rs = statement.executeQuery();
            if (rs.next()) {
                return resultSetToUser(rs, false);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find user by id " + keyValue ,e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public User create(final User object) throws DAOException {
        try {
            open();
            object.setCreatedDate(new Date(System.currentTimeMillis()));
            statement = conn.prepareStatement(getQuery("create_user"));
            statement.setString(1,object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setString(3, object.getEmail());
            statement.setBoolean(4, object.isGender());
            statement.setString(5, object.getUsername());
            statement.setString(6, object.getPassword());
            statement.setDate(7, toSqlDate(object.getCreatedDate()));
            statement.executeUpdate();
            statement = conn.prepareStatement("select last_insert_id() as last_id;");
            rs = statement.executeQuery();
            if (rs.next()) {
                object.initAdditionalData();
                object.setId(rs.getInt("last_id"));
                return object;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not create user " + object.getUsername(), e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public User update(User object) throws DAOException {
        try {
            open();
            if (object.getPassword().length() > 0) {
                statement = conn.prepareStatement(getQuery("update_user_with_password"));
                statement.setString(1,object.getFirstName());
                statement.setString(2, object.getLastName());
                statement.setString(3, object.getEmail());
                statement.setBoolean(4, object.isGender());
                statement.setString(5, object.getUsername());
                statement.setString(6, object.getPassword());
                statement.setInt(7,object.getId());
            } else {
                statement = conn.prepareStatement(getQuery("update_user"));
                statement.setString(1,object.getFirstName());
                statement.setString(2, object.getLastName());
                statement.setString(3, object.getEmail());
                statement.setBoolean(4, object.isGender());
                statement.setString(5, object.getUsername());
                statement.setInt(6,object.getId());
            }

            statement.executeUpdate();
            return object;
        } catch (SQLException e) {
            throw new DAOException("Could not update user " + object.getUsername(), e);
        } finally {
            close();
        }
    }

    @Override
    public void delete(User object) throws DAOException {
        delete(object.getId());
    }

    @Override
    public void delete(Integer objectId) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_user"));
            statement.setInt(1,objectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Could not update user ID " + objectId, e);
        } finally {
            close();
        }
    }

    @Override
    public void updatePassword(User user) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("update_user_password"));
            statement.setString(1,user.getPassword());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Could not update user " + user.getUsername() + " password", e);
        } finally {
            close();
        }
    }

    @Override
    public User findUserByUsernamePassword(String username, String password) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("find_one_user_by_username_password"));
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();
            if (rs.next()) {
                return resultSetToUser(rs, false);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find user with username " + username ,e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Collection<Role> findUserRole(User user, boolean closeConn) throws DAOException {
        return findUserRole(user.getId(), closeConn);
    }

    @Override
    public Collection<Role> findUserRole(int userId, boolean closeConn) throws DAOException {
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            open();
            stm = conn.prepareStatement(getQuery("find_mapping_user_role"));
            stm.setInt(1, userId);
            resultSet = stm.executeQuery();
            if (resultSet.next()) {
                Collection<Role> roles = new ArrayList<Role>();
                do {
                    Role role = new Role();
                    role.setName(resultSet.getString("name"));
                    role.setId(resultSet.getInt("role_id"));
                    roles.add(role);
                } while (resultSet.next());
                return roles;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not list role of user id " + userId, e);
        } finally {
            if (closeConn) {
                DatabaseConnector.close(conn, stm, resultSet);
            } else {
                DatabaseConnector.close(null, stm, resultSet);
            }
        }
        return null;
    }

    @Override
    public boolean mapRole(User user, Role role) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("create_user_role_mapping"));
            statement.setInt(1, user.getId());
            statement.setInt(2, role.getId());
            statement.setDate(3, toSqlDate(new Date(System.currentTimeMillis())));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not map user id " + user.getId() + " to role id " + role.getId(),e);
        } finally {
            close();
        }
    }

    @Override
    public boolean unmapRole(User user, Role role) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_user_role_mapping"));
            statement.setInt(1, user.getId());
            statement.setInt(2, role.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not unmap user id " + user.getId() + " with role id " + role.getId(),e);
        } finally {
            close();
        }
    }

    @Override
    public boolean removeAllRoleMapping(User user) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_all_user_role_mapping"));
            statement.setInt(1, user.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not remove all mapping role of  user id " + user.getId(),e);
        } finally {
            close();
        }
    }

}
