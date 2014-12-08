package uk.ac.gre.cw.aircraft.services;

import uk.ac.gre.cw.aircraft.Common;
import uk.ac.gre.cw.aircraft.entities.Role;
import uk.ac.gre.cw.aircraft.entities.User;

import java.util.Collection;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class AbstractService<T> {
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    private User currentUser;

    public AbstractService() {

    }

    public AbstractService(User currentUser) {
        this.currentUser = currentUser;
    }

    public abstract Collection<T> findAll() throws ServiceException;

    public abstract void delete(T object) throws ServiceException;

    public abstract void delete(int id) throws ServiceException;

    public abstract T create(T object) throws ServiceException;

    public abstract T update(T object) throws ServiceException;

    public User getCurrentUser() {
        return currentUser;
    }

    protected boolean checkRole(Role role) {
        if (Common.DEBUG) return true;
        if (currentUser == null) return false;
        return currentUser.containRole(role);
    }
}
