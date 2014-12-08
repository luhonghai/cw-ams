package uk.ac.gre.cw.aircraft.dao;

import uk.ac.gre.cw.aircraft.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.entities.Engineer;

public interface IQualificationDAO<Qualification,Integer> extends IDAO<Qualification,Integer> {

    public void findQualifications(final Engineer user) throws DAOException;

    public void removeAllMappingEngineer(int qualificationId) throws DAOException;
}
