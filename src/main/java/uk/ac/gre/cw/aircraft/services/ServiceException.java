package uk.ac.gre.cw.aircraft.services;

public class ServiceException extends Exception  {

    public ServiceException() {
        this(null);
    }

    public ServiceException(String message) {
        this(message, null);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
