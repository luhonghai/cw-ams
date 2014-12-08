package uk.ac.gre.cw.aircraft.dao;

import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Engineer;
import uk.ac.gre.cw.aircraft.entities.Job;
import uk.ac.gre.cw.aircraft.entities.Qualification;

public interface IEngineerDAO {

    public boolean mapQualification(Engineer user, Qualification qualification) throws DAOException;

    public boolean unmapQualification(Engineer user, Qualification qualification) throws DAOException;

    public boolean mapJob(Engineer user, Job job) throws DAOException;

    public boolean unmapJob(Engineer user, Job job) throws DAOException;

    public void findEngineers(Job job) throws DAOException;
}
