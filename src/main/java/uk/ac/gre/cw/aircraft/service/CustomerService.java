package uk.ac.gre.cw.aircraft.service;

import uk.ac.gre.cw.aircraft.jdo.dao.ICustomerDAO;
import uk.ac.gre.cw.aircraft.jdo.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.jdo.dao.impl.CustomerDao;
import uk.ac.gre.cw.aircraft.jdo.entities.Customer;
import uk.ac.gre.cw.aircraft.utils.MD5Helper;

/**
 * Created by Long Nguyen on 12/4/14.
 */
public class CustomerService {
    private ICustomerDAO customerDAO = new CustomerDao();

    public Customer login(String email, String password) {
        return customerDAO.findByEmailPassword(email, MD5Helper.md5(password));
    }

    public boolean create(final Customer customer) throws Exception{
        customer.setPassword(MD5Helper.md5(customer.getPassword()));
        try {
            customerDAO.save(customer);
            return true;
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer getByEmail(String email) {
        return customerDAO.findByEmail(email);
    }
}
