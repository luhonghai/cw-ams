package uk.ac.gre.cw.aircraft.dao.impl;

import uk.ac.gre.cw.aircraft.dao.AbstractDAO;
import uk.ac.gre.cw.aircraft.dao.DatabaseConnector;
import uk.ac.gre.cw.aircraft.dao.IJobDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class JobDaoImpl extends AbstractDAO<Job,Integer> implements IJobDAO<Job,Integer> {

    @Override
    public Collection<Job> findAll() throws DAOException {
        try {
            open();
            conn = DatabaseConnector.open();
            statement = conn.prepareStatement(getQuery("find_all_jobs"));
            rs = statement.executeQuery();
            if (rs.next()) {
                Collection<Job> jobs = new ArrayList<>();
                do {
                    jobs.add(resultSetToJob(rs));
                } while (rs.next());
                return jobs;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find all job",e);
        } finally {
            close();
        }
        return null;
    }

    protected Job resultSetToJob(final ResultSet rs) throws SQLException {
        Job job = new Job();
        job.setId(rs.getInt("job_id"));
        job.setName(rs.getString("name"));
        job.setDescription(rs.getString("description"));
        job.setNumberOfEngineer(rs.getInt("number_of_engineer"));
        job.setCreatedDate(getDate(rs, "created_date"));
        /**
         *  Get Job status
         */
        int statusId = rs.getInt("status_id");
        if (!rs.wasNull()) {
            JobStatus status = new JobStatus();
            status.setName(rs.getString("job_status_name"));
            status.setId(statusId);
            status.setLevel(rs.getFloat("job_status_level"));
            job.setStatus(status);
        }
        /**
         *  Get Job Qualification
         */
        int qualId = rs.getInt("qualification_id");
        if (!rs.wasNull()) {
            Qualification qualification = new Qualification();
            qualification.setId(qualId);
            qualification.setName(rs.getString("qualification_name"));
            qualification.setDescription(rs.getString("qualification_description"));
            qualification.setCreatedDate(getDate(rs, "qualification_created_date"));
            job.setQualification(qualification);
        }
        /**
         *  Get Job Priority
         */
        int priorityId = rs.getInt("priority_id");
        if (!rs.wasNull()) {
            Priority priority = new Priority();
            priority.setId(priorityId);
            priority.setName(rs.getString("priority_name"));
            priority.setLevel(rs.getFloat("priority_level"));
            job.setPriority(priority);
        }
        job.initAdditionalData();
        return job;
    }

    @Override
    public Job findOne(Integer keyValue) throws DAOException {
        try {
            open();
            conn = DatabaseConnector.open();
            statement = conn.prepareStatement(getQuery("find_one_job_by_id"));
            statement.setInt(1, keyValue);
            rs = statement.executeQuery();
            if (rs.next()) {
                return resultSetToJob(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find job by ID " + keyValue,e );
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Job create(final Job object) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("create_job"));
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            statement.setInt(3, object.getNumberOfEngineer());
            if (object.getQualification() != null) {
                statement.setInt(4, object.getQualification().getId());
            } else {
                statement.setNull(4, Types.NULL);
            }
            if (object.getPriority() != null) {
                statement.setInt(5, object.getPriority().getId());
            } else {
                statement.setNull(5, Types.NULL);
            }
            if (object.getStatus() != null) {
                statement.setInt(6, object.getStatus().getId());
            } else {
                statement.setNull(6, Types.NULL);
            }
            object.setCreatedDate(new Date(System.currentTimeMillis()));
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
            throw new DAOException("Could not create job " + object.getName(), e );
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Job update(final Job object) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("update_job"));
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            statement.setInt(3, object.getNumberOfEngineer());
            if (object.getQualification() != null) {
                statement.setInt(4, object.getQualification().getId());
            } else {
                statement.setNull(4, Types.NULL);
            }
            if (object.getPriority() != null) {
                statement.setInt(5, object.getPriority().getId());
            } else {
                statement.setNull(5, Types.NULL);
            }
            if (object.getStatus() != null) {
                statement.setInt(6, object.getStatus().getId());
            } else {
                statement.setNull(6, Types.NULL);
            }
            statement.setInt(7, object.getId());
            int result = statement.executeUpdate();
            if (result > 0) {
                object.initAdditionalData();
                return object;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not update job " + object.getName(), e );
        } finally {
            close();
        }
        return null;
    }

    @Override
    public void delete(Job object) throws DAOException {
        delete(object.getId());
    }

    @Override
    public void delete(Integer objectId) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_job"));
            statement.setInt(1, objectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Could not update job ID " + objectId, e );
        } finally {
            close();
        }
    }

    @Override
    public void findJobs(final Engineer user) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("find_engineer_jobs"));
            statement.setInt(1, user.getId());
            rs = statement.executeQuery();
            if (rs.next()) {
                Collection<Job> jobs = new ArrayList<>();
                do {
                    jobs.add(resultSetToJob(rs));
                } while (rs.next());
                user.setJobs(jobs);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find all jobs of engineer id " + user.getId(), e);
        } finally {
            close();
        }
    }
}
