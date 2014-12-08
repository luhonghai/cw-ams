package uk.ac.gre.cw.aircraft.dao;

import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Engineer;

public interface IJobDAO<Job,Integer> extends IDAO<Job,Integer> {

    public void findJobs(final Engineer user) throws DAOException;

    public void removeAllMappingEngineer(int jobId) throws DAOException;
}
