package uk.ac.gre.cw.aircraft.dao;

import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Engineer;
import uk.ac.gre.cw.aircraft.entities.Job;
import uk.ac.gre.cw.aircraft.entities.Qualification;
import uk.ac.gre.cw.aircraft.entities.User;
import uk.ac.gre.cw.aircraft.hanlder.MappingData;

import java.util.Collection;

public interface IEngineerDAO {

    public boolean createEngineer(Engineer user) throws DAOException;



    public boolean removeAllMappingQualification(int userId) throws DAOException;

    public boolean unmapQualification(Engineer user, Qualification qualification) throws DAOException;

    public boolean mapJob(int userId, int jobId) throws DAOException;

    public boolean mapQualification(int userId, int qualificationId) throws DAOException;

    public boolean mapJob(Engineer user, Job job) throws DAOException;

    public boolean mapQualification(Engineer user, Qualification qualification) throws DAOException;

    public boolean removeAllMappingJob(int userId) throws DAOException;

    public boolean unmapJob(Engineer user, Job job) throws DAOException;

    public void findEngineers(Job job) throws DAOException;

    public MappingData findMapping(MappingData data, String query) throws DAOException;
}
