package uk.ac.gre.cw.aircraft.dao.impl;

import uk.ac.gre.cw.aircraft.dao.AbstractDAO;
import uk.ac.gre.cw.aircraft.dao.IQualificationDAO;
import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Engineer;
import uk.ac.gre.cw.aircraft.entities.Qualification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class QualificationDaoImpl extends AbstractDAO<Qualification, Integer> implements IQualificationDAO<Qualification, Integer> {

    @Override
    public Collection<Qualification> findAll() throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("find_all_qualification"));
            rs = statement.executeQuery();
            if (rs.next()) {
                Collection<Qualification> qualifications = new ArrayList<Qualification>();
                do {
                    qualifications.add(resultSetToQualification(rs));
                } while (rs.next());
                return qualifications;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find all qualification",e);
        } finally {
            close();
        }
        return null;
    }

    protected Qualification resultSetToQualification(ResultSet rs) throws SQLException {
        Qualification qualification = new Qualification();
        qualification.setId(rs.getInt("qualification_id"));
        qualification.setDescription(rs.getString("description"));
        qualification.setName(rs.getString("name"));
        qualification.setCreatedDate(getDate(rs, "created_date"));
        qualification.initAdditionalData();
        return qualification;
    }

    @Override
    public Qualification findOne(Integer keyValue) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("find_one_qualification_by_id"));
            statement.setInt(1, keyValue);
            rs = statement.executeQuery();
            if (rs.next()) {
                return resultSetToQualification(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not find all qualification",e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Qualification create(Qualification object) throws DAOException {
        try {
            open();
            object.setCreatedDate(new Date(System.currentTimeMillis()));
            statement = conn.prepareStatement(getQuery("create_qualification"));
            statement.setString(1,object.getName());
            statement.setString(2, object.getDescription());
            statement.setDate(3, toSqlDate(object.getCreatedDate()));
            statement.executeUpdate();
            statement = conn.prepareStatement("select last_insert_id() as last_id;");
            rs = statement.executeQuery();
            if (rs.next()) {
                object.initAdditionalData();
                object.setId(rs.getInt("last_id"));
                return object;
            }
        } catch (SQLException e) {
            throw new DAOException("Could not create qualification name " +object.getName(),e);
        } finally {
            close();
        }
        return null;
    }

    @Override
    public Qualification update(Qualification object) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("update_qualification"));
            statement.setString(1,object.getName());
            statement.setString(2, object.getDescription());
            statement.setInt(3, object.getId());
            statement.executeUpdate();
            object.initAdditionalData();
            return object;
        } catch (SQLException e) {
            throw new DAOException("Could not update qualification name " +object.getName(),e);
        } finally {
            close();
        }
    }

    @Override
    public void delete(Qualification object) throws DAOException {
        delete(object.getId());
    }

    @Override
    public void delete(Integer objectId) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_qualification"));
            statement.setInt(1, objectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Could not delete qualification id " + objectId,e);
        } finally {
            close();
        }
    }

    @Override
    public void findQualifications(final Engineer user) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("find_engineer_qualifications"));
            statement.setInt(1, user.getId());
            rs = statement.executeQuery();
            if (rs.next()) {
                Collection<Qualification> qualifications = new ArrayList<Qualification>();
                do {
                    qualifications.add(resultSetToQualification(rs));
                } while (rs.next());
                user.setQualifications(qualifications);
            }
        } catch (SQLException e) {
            throw new DAOException("Could find all qualification of user id " + user.getId(),e);
        } finally {
            close();
        }
    }

    @Override
    public void removeAllMappingEngineer(int qualificationId) throws DAOException {
        try {
            open();
            statement = conn.prepareStatement(getQuery("delete_all_qualification_engineer_mapping"));
            statement.setInt(1, qualificationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Could not delete all engineer mapping " + qualificationId,e);
        } finally {
            close();
        }
    }
}
