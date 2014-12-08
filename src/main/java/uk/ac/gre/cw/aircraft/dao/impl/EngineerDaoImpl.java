package uk.ac.gre.cw.aircraft.dao.impl;

import uk.ac.gre.cw.aircraft.dao.IEngineerDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Engineer;
import uk.ac.gre.cw.aircraft.entities.Job;
import uk.ac.gre.cw.aircraft.entities.Qualification;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class EngineerDaoImpl extends UserDaoImpl implements IEngineerDAO {

    @Override
    public boolean mapQualification(Engineer user, Qualification qualification) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("create_engineer_qualification_mapping"));
            statement.setInt(1, user.getId());
            statement.setInt(2, qualification.getId());
            statement.setDate(3, toSqlDate(new Date(System.currentTimeMillis())));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not map user id " + user.getId() + " to qualification id " + qualification.getId(),e);
        } finally {
            close();
        }
    }

    @Override
    public boolean unmapQualification(Engineer user, Qualification qualification) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_engineer_qualification_mapping"));
            statement.setInt(1, user.getId());
            statement.setInt(2, qualification.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not unmap user id " + user.getId() + " with qualification id " + qualification.getId(),e);
        } finally {
            close();
        }
    }

    @Override
    public boolean mapJob(Engineer user, Job job) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("create_engineer_job_mapping"));
            statement.setInt(1, user.getId());
            statement.setInt(2, job.getId());
            statement.setDate(3, toSqlDate(new Date(System.currentTimeMillis())));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not map user id " + user.getId() + " to job id " + job.getId(),e);
        } finally {
            close();
        }
    }

    @Override
    public boolean unmapJob(Engineer user, Job job) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_engineer_job_mapping"));
            statement.setInt(1, user.getId());
            statement.setInt(2, job.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not unmap user id " + user.getId() + " with job id " + job.getId(),e);
        } finally {
            close();
        }
    }

    @Override
    public void findEngineers(final Job job) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("find_job_engineers"));
            statement.setInt(1, job.getId());
            rs = statement.executeQuery();
            if (rs.next()) {
                Collection<Engineer> engineers = new ArrayList<>();
                do {
                    Engineer engineer = (Engineer) resultSetToUser(rs, false);
                    engineer.setAvailable(rs.getBoolean("availability"));
                    engineers.add(engineer);
                } while (rs.next());
                job.setEngineers(engineers);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find all engineers of job id " + job.getId(), e);
        } finally {
            close();
        }
    }
}
