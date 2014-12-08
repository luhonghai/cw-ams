package uk.ac.gre.cw.aircraft.services;

import com.google.gson.Gson;
import uk.ac.gre.cw.aircraft.dao.IEngineerDAO;
import uk.ac.gre.cw.aircraft.dao.IQualificationDAO;
import uk.ac.gre.cw.aircraft.dao.IUserDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.dao.impl.EngineerDaoImpl;
import uk.ac.gre.cw.aircraft.dao.impl.QualificationDaoImpl;
import uk.ac.gre.cw.aircraft.dao.impl.UserDaoImpl;
import uk.ac.gre.cw.aircraft.entities.*;
import uk.ac.gre.cw.aircraft.hanlder.MappingData;
import uk.ac.gre.cw.aircraft.utils.MD5Helper;

import java.util.ArrayList;
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

    public User update(User user) throws ServiceException {
        if (checkRole(Role.ADMINISTRATOR)) {
            IUserDAO<User, Integer> userDAO = new UserDaoImpl();
            try {
                if (user.getPassword().length() > 0) {
                    user.setPassword(MD5Helper.md5(user.getPassword()));
                }
                userDAO.update(user);
                updateMapping(user);
                return userDAO.findOne(user.getId());
            } catch (DAOException e) {
                logger.log(Level.SEVERE, "Could not update user " + user.getUsername(), e);
                throw new ServiceException("Could not update user. Message: " + e.getMessage());
            }
        } else {
            throw new ServiceException("Require Administrator role");
        }
    }

    public void updateMapping(User user) throws ServiceException {

    }

    public User create(User user) throws ServiceException {
        if (checkRole(Role.ADMINISTRATOR)) {
            Gson gson = new Gson();
            IUserDAO<User, Integer> userDAO = new UserDaoImpl();
            user.setPassword(MD5Helper.md5(user.getPassword()));
            try {
                user = userDAO.create(user);
                userDAO.mapRole(user, Role.ENGINEER);
                user = userDAO.findOne(user.getId());
                if (user.containRole(Role.ENGINEER)) {
                    IEngineerDAO engineerDAO = new EngineerDaoImpl();
                    Engineer engineer =gson.fromJson(gson.toJson(user),Engineer.class);
                    engineer.setAvailable(true);
                    engineerDAO.createEngineer(engineer);
                }
                updateMapping(user);
                return user;
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

    public MappingData findMappingEngineerJob(MappingData data) throws ServiceException {
        try {
            IEngineerDAO engineerDAO = new EngineerDaoImpl();
            MappingData tmp = engineerDAO.findMapping(data, "find_mapping_engineer_job");
            if (tmp != null) {
                data = tmp;
            }
            JobService jobService = new JobService();
            Collection<Job> jobs = jobService.findAll();
            Collection<MappingData.Mapping> mappings = new ArrayList<MappingData.Mapping>();
            if (jobs != null && jobs.size() > 0) {
                for (Job job : jobs) {
                    if (data != null && data.isContainMapping(job.getId())) {
                        mappings.add(new MappingData.Mapping(job.getId(), job.getName(), true));
                    } else {
                        mappings.add(new MappingData.Mapping(job.getId(), job.getName(), false));
                    }
                }
            }
            data.setMappings(mappings);
            return data;
        } catch (DAOException e) {
           throw new ServiceException("Could not found mapping engineer-job", e);
        }
    }

    public MappingData findMappingQualificationEngineer(MappingData data) throws ServiceException {
        try {
            IEngineerDAO engineerDAO = new EngineerDaoImpl();
            MappingData tmp = engineerDAO.findMapping(data, "find_mapping_qualification_engineer");
            if (tmp != null) {
                data = tmp;
            }
            Collection<User> users = findAll();
            Collection<MappingData.Mapping> mappings = new ArrayList<MappingData.Mapping>();
            if (users != null && users.size() > 0) {
                for (User user : users) {
                    if (data != null && data.isContainMapping(user.getId())) {
                        mappings.add(new MappingData.Mapping(user.getId(),
                                user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")", true));
                    } else {
                        mappings.add(new MappingData.Mapping(user.getId(),
                                user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")", false));
                    }
                }
            }
            data.setMappings(mappings);
            return data;
        } catch (DAOException e) {
            throw new ServiceException("Could not found mapping qualification-engineer", e);
        }
    }

    public MappingData findMappingEngineerQualification(MappingData data) throws ServiceException {
        try {
            IEngineerDAO engineerDAO = new EngineerDaoImpl();
            MappingData tmp = engineerDAO.findMapping(data, "find_mapping_engineer_qualification");
            if (tmp != null) {
                data = tmp;
            }
            IQualificationDAO<Qualification, Integer> qualificationDAO = new QualificationDaoImpl();
            Collection<Qualification> qualifications = qualificationDAO.findAll();
            Collection<MappingData.Mapping> mappings = new ArrayList<MappingData.Mapping>();
            if (qualifications != null && qualifications.size() > 0) {
                for (Qualification qualification  : qualifications) {
                    if (data != null && data.isContainMapping(qualification.getId())) {
                        mappings.add(new MappingData.Mapping(qualification.getId(),
                                qualification.getName(), true));
                    } else {
                        mappings.add(new MappingData.Mapping(qualification.getId(),
                                qualification.getName(), false));
                    }
                }
            }
            data.setMappings(mappings);
            return data;
        } catch (DAOException e) {
            throw new ServiceException("Could not found mapping engineer-qualification", e);
        }
    }

    public MappingData findMappingJobEngineer(MappingData data) throws ServiceException {
        try {
            IEngineerDAO engineerDAO = new EngineerDaoImpl();
            MappingData tmp = engineerDAO.findMapping(data, "find_mapping_job_engineer");
            if (tmp != null) {
                data = tmp;
            }
            Collection<User> users = findAll();
            Collection<MappingData.Mapping> mappings = new ArrayList<MappingData.Mapping>();
            if (users != null && users.size() > 0) {
                for (User user : users) {
                    if (data != null && data.isContainMapping(user.getId())) {
                        mappings.add(new MappingData.Mapping(user.getId(),
                                user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")", true));
                    } else {
                        mappings.add(new MappingData.Mapping(user.getId(),
                                user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")", false));
                    }
                }
            }
            data.setMappings(mappings);
            return data;
        } catch (DAOException e) {
            throw new ServiceException("Could not found mapping job-engineer", e);
        }
    }
}
