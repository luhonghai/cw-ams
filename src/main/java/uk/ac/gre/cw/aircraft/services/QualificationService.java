package uk.ac.gre.cw.aircraft.services;

import uk.ac.gre.cw.aircraft.dao.IQualificationDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.dao.impl.QualificationDaoImpl;
import uk.ac.gre.cw.aircraft.entities.Qualification;

import java.util.Collection;
import java.util.logging.Level;

public class QualificationService extends AbstractService<Qualification> {

    public Collection<Qualification> findAll() throws ServiceException {
        try {
            IQualificationDAO<Qualification,Integer> qualificationDAO = new QualificationDaoImpl();
            return qualificationDAO.findAll();
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not list all qualification", e);
            throw new ServiceException("Could not list all qualification", e);
        }
    }

    public void delete(Qualification qualification) throws ServiceException {
        delete(qualification.getId());
    }

    public void delete(int qualificationId) throws ServiceException {
        try {
            IQualificationDAO<Qualification,Integer> qualificationDAO = new QualificationDaoImpl();
            qualificationDAO.delete(qualificationId);
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not delete qualification id " + qualificationId, e);
            throw new ServiceException("Could not delete qualification id " + qualificationId, e);
        }
    }

    public Qualification update(Qualification qualification) throws ServiceException {
        try {
            IQualificationDAO<Qualification,Integer> qualificationDAO = new QualificationDaoImpl();
            qualificationDAO.update(qualification);
            return qualificationDAO.findOne(qualification.getId());
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not update qualification " + qualification.getId(), e);
            throw new ServiceException("Could not update qualification id " + qualification.getId(), e);
        }
    }

    public Qualification create(Qualification qualification) throws ServiceException {
        try {
            IQualificationDAO<Qualification,Integer> qualificationDAO = new QualificationDaoImpl();
            return qualificationDAO.create(qualification);
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not create qualification " + qualification.getName(), e);
            throw new ServiceException("Could not create qualification " + qualification.getName(), e);
        }
    }
}
