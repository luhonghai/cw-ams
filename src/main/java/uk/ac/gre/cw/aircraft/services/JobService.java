package uk.ac.gre.cw.aircraft.services;

import uk.ac.gre.cw.aircraft.dao.IEngineerDAO;
import uk.ac.gre.cw.aircraft.dao.IJobDAO;
import uk.ac.gre.cw.aircraft.dao.IQualificationDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.dao.impl.EngineerDaoImpl;
import uk.ac.gre.cw.aircraft.dao.impl.JobDaoImpl;
import uk.ac.gre.cw.aircraft.dao.impl.QualificationDaoImpl;
import uk.ac.gre.cw.aircraft.entities.Job;
import uk.ac.gre.cw.aircraft.entities.Qualification;
import uk.ac.gre.cw.aircraft.hanlder.MappingData;

import java.util.Collection;
import java.util.logging.Level;

public class JobService extends AbstractService<Job> {

    public Collection<Job> findAll() throws ServiceException {
        IJobDAO<Job,Integer> jobDAO = new JobDaoImpl();
        try {
            return jobDAO.findAll();
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not list all jobs", e);
            throw new ServiceException("Could not list all jobs");
        }
    }

    public void delete(Job job) throws ServiceException {
        delete(job.getId());
    }

    public void delete(int jobId) throws ServiceException {
        IJobDAO<Job,Integer> jobDAO = new JobDaoImpl();
        try {
            jobDAO.delete(jobId);
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not delete job id " + jobId, e);
            throw new ServiceException("Could not delete job id " + jobId);
        }
    }

    public Job create(Job job) throws ServiceException {
        IJobDAO<Job,Integer> jobDAO = new JobDaoImpl();
        try {
            job = jobDAO.create(job);
            if (job != null) {
                updateMapping(job);
                return jobDAO.findOne(job.getId());
            }
            return null;
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not delete job " + job.getName(), e);
            throw new ServiceException("Could not delete job " + job.getName());
        }
    }

    public void updateMapping(Job job) throws ServiceException {
        IJobDAO<Job,Integer> jobDAO = new JobDaoImpl();
        IEngineerDAO engineerDAO = new EngineerDaoImpl();
        try {
            jobDAO.removeAllMappingEngineer(job.getId());
            Collection<MappingData.Mapping> mappings = job.getEngineerMapping();
            if (mappings!=null && mappings.size() > 0) {
                for (MappingData.Mapping m : mappings) {
                    engineerDAO.mapJob(m.getId(), job.getId());
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Could not mapping job-engineer", e);
        }
    }

    public Job update(Job job) throws ServiceException {
        IJobDAO<Job,Integer> jobDAO = new JobDaoImpl();
        try {
            jobDAO.update(job);
            updateMapping(job);
            return jobDAO.findOne(job.getId());
        } catch (DAOException e) {
            logger.log(Level.SEVERE, "Could not delete job " + job.getName(), e);
            throw new ServiceException("Could not delete job " + job.getName());
        }
    }
}
