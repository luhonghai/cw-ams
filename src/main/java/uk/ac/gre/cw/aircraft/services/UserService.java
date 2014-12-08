package uk.ac.gre.cw.aircraft.services;

import uk.ac.gre.cw.aircraft.dao.IUserDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.dao.impl.UserDaoImpl;
import uk.ac.gre.cw.aircraft.entities.Role;
import uk.ac.gre.cw.aircraft.entities.User;
import uk.ac.gre.cw.aircraft.utils.MD5Helper;

import java.util.Collection;
import java.util.logging.Level;

public class UserService extends AbstractService<User> {

    public User login(String username, String password) throws ServiceException {
        if (username == null || username.length() == 0 || password == null || password.length() == 0) return null;
        IUserDAO<User, Integer> userDAO = new UserDaoImpl();
        try {
            return userDAO.findUserByUsernamePassword(username, MD5Helper.md5(password));
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not login username " + username, e);
            throw new ServiceException("Invalid username or password", e);
        }
    }

    public User create(User user) throws ServiceException {
        if (checkRole(Role.ADMINISTRATOR)) {
            IUserDAO<User, Integer> userDAO = new UserDaoImpl();
            user.setPassword(MD5Helper.md5(user.getPassword()));
            try {
                user = userDAO.create(user);
                userDAO.mapRole(user, Role.ENGINEER);
                return userDAO.findOne(user.getId());
            } catch (DAOException e) {
                logger.log(Level.SEVERE, "Could not create user " + user.getUsername(), e);
                throw new ServiceException("Could not create user. Message: " + e.getMessage());
            }
        } else {
            throw new ServiceException("Require Administrator role");
        }
    }

    public Collection<User> findAll() throws ServiceException {
        try {
            IUserDAO<User,Integer> userDAO = new UserDaoImpl();
            return userDAO.findAll();
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not find all users", e);
            throw new ServiceException("Could not find all users", e);
        }
    }

    public User update(User user) throws ServiceException {
        if (checkRole(Role.ADMINISTRATOR)) {
            IUserDAO<User, Integer> userDAO = new UserDaoImpl();
            try {
                if (user.getPassword().length() > 0) {
                    user.setPassword(MD5Helper.md5(user.getPassword()));
                }
                userDAO.update(user);
                return userDAO.findOne(user.getId());
            } catch (DAOException e) {
                logger.log(Level.SEVERE, "Could not update user " + user.getUsername(), e);
                throw new ServiceException("Could not update user. Message: " + e.getMessage());
            }
        } else {
            throw new ServiceException("Require Administrator role");
        }
    }

    public void delete(User user) throws ServiceException {
        delete(user.getId());
    }

    public void delete(int userId) throws ServiceException {
        if (checkRole(Role.ADMINISTRATOR)) {
            IUserDAO<User, Integer> userDAO = new UserDaoImpl();
            try {
                userDAO.delete(userId);
            } catch (DAOException e) {
                logger.log(Level.SEVERE, "Could not delete user id " +userId, e);
                throw new ServiceException("Could not delete user.",e);
            }
        } else {
            throw new ServiceException("Require Administrator role");
        }
    }

    /**
     *  Change current login user password
     * @throws ServiceException
     */
    public void changePassword() throws ServiceException {
        if (getCurrentUser() != null) {
            IUserDAO<User, Integer> userDAO = new UserDaoImpl();
            getCurrentUser().setPassword(MD5Helper.md5(getCurrentUser().getPassword()));
            try {
                userDAO.updatePassword(getCurrentUser());
            } catch (DAOException e) {
                logger.log(Level.SEVERE, "Could not change user id " + getCurrentUser().getId() + " password.", e);
                throw new ServiceException("Could not change user password. Message: " + e.getMessage());
            }
        } else {
            throw new ServiceException("Require login to access");
        }

    }

    /**
     * Change other user password require administrator role
     * @param user
     * @throws ServiceException
     */
    public void changePassword(User user) throws ServiceException {
        if (checkRole(Role.ADMINISTRATOR)) {
            IUserDAO<User, Integer> userDAO = new UserDaoImpl();
            user.setPassword(MD5Helper.md5(user.getPassword()));
            try {
                userDAO.updatePassword(user);
            } catch (DAOException e) {
                logger.log(Level.SEVERE, "Could not change user id " + user.getId() + " password.", e);
                throw new ServiceException("Could not change user password. Message: " + e.getMessage());
            }
        } else {
            throw new ServiceException("Require Administrator role");
        }
    }
}
