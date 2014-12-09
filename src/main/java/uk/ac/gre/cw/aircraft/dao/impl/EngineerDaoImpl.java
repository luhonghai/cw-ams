package uk.ac.gre.cw.aircraft.dao.impl;

import uk.ac.gre.cw.aircraft.dao.DatabaseConnector;
import uk.ac.gre.cw.aircraft.dao.IEngineerDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Engineer;
import uk.ac.gre.cw.aircraft.entities.Job;
import uk.ac.gre.cw.aircraft.entities.Qualification;
import uk.ac.gre.cw.aircraft.entities.User;
import uk.ac.gre.cw.aircraft.hanlder.MappingData;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class EngineerDaoImpl extends UserDaoImpl implements IEngineerDAO {

    @Override
    public boolean createEngineer(Engineer engineer) throws DAOException {
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            open();
            stm = conn.prepareStatement(getQuery("find_one_engineer"));
            stm.setInt(1, engineer.getId());
            resultSet = stm.executeQuery();
            if (!resultSet.next()) {
                statement = conn.prepareStatement(getQuery("create_engineer"));
                statement.setInt(1, engineer.getId());
                statement.setBoolean(2, engineer.isAvailable());
                return statement.executeUpdate() > 0;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not create engineer id " + engineer.getId(),e);
        } finally {
            DatabaseConnector.close(null, stm, resultSet);
            close();
        }
    }

    @Override
    public boolean mapQualification(Engineer user, Qualification qualification) throws DAOException {
        return mapQualification(user.getId(), qualification.getId());
    }

    @Override
    public boolean removeAllMappingQualification(int userId) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_all_engineer_qualification_mapping"));
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not delete all qualification mapping " + userId,e);
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
    public boolean mapJob(int userId, int jobId) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("create_engineer_job_mapping"));
            statement.setInt(1, userId);
            statement.setInt(2, jobId);
            statement.setDate(3, toSqlDate(new Date(System.currentTimeMillis())));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not map user id " + userId + " to job id " + jobId,e);
        } finally {
            close();
        }
    }

    @Override
    public boolean mapQualification(int userId, int qualificationId) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("create_engineer_qualification_mapping"));
            statement.setInt(1, userId);
            statement.setInt(2, qualificationId);
            statement.setDate(3, toSqlDate(new Date(System.currentTimeMillis())));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not map user id " + userId + " to qualification id " + qualificationId,e);
        } finally {
            close();
        }
    }

    @Override
    public boolean mapJob(Engineer user, Job job) throws DAOException {
        return mapJob(user.getId(), job.getId());
    }

    @Override
    public boolean removeAllMappingJob(int userId) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_all_engineer_job_mapping"));
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Could not delete all job mapping " + userId,e);
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
                Collection<Engineer> engineers = new ArrayList<Engineer>();
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

    @Override
    public MappingData findMapping(MappingData data, String query) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery(query));
            statement.setInt(1, data.getId());
            rs = statement.executeQuery();
            if (rs.next()) {
                do {
                    data.addMapping(new MappingData.Mapping(rs.getInt("m_id"), true));
                } while (rs.next());
                return data;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find all mapping for " + data.getId() + " query: " + query, e);
        } finally {
            close();
        }
        return null;
    }
}
