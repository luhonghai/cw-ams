package uk.ac.gre.cw.aircraft.jdo.dao.impl;

import uk.ac.gre.cw.aircraft.jdo.dao.AbstractDAO;
import uk.ac.gre.cw.aircraft.jdo.dao.ICustomerDAO;
import uk.ac.gre.cw.aircraft.jdo.entities.Customer;

import javax.jdo.Query;

/**
 * Created by Long Nguyen on 12/4/14.
 */
public class CustomerDao extends AbstractDAO<Customer, Long> implements ICustomerDAO {

    public CustomerDao() {
        super(Customer.class);
    }

    @Override
    public Customer findByEmail(String email) {
        Query query = getPM().newQuery(Customer.class);
        query.setFilter("email.equals(value)");
        query.declareParameters("java.lang.String value");

        return this.processQueryResult(query, email);
    }

    @Override
    public Customer findByEmailPassword(String email, String password) {
        Query query = getPM().newQuery(Customer.class);
        query.setFilter("email.equals(valEmail) && password.equals(valPassword)");
        query.declareParameters("java.lang.String valEmail, java.lang.String valPassword");
        return this.processQueryResult(query, email, password);
    }
}
