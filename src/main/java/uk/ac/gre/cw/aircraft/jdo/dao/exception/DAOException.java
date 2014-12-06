package uk.ac.gre.cw.aircraft.jdo.dao.exception;

/**
 * Created by Long Nguyen on 12/4/14.
 */
public class DAOException extends Exception  {

    public DAOException() {
        this(null);
    }

    public DAOException(String message) {
        this(message, null);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
