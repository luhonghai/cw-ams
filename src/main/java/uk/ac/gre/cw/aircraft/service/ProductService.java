package uk.ac.gre.cw.aircraft.service;

import uk.ac.gre.cw.aircraft.jdo.dao.IProductDAO;
import uk.ac.gre.cw.aircraft.jdo.dao.exception.DAOException;
import uk.ac.gre.cw.aircraft.jdo.dao.impl.ProductDao;
import uk.ac.gre.cw.aircraft.jdo.entities.Product;

import java.util.Collection;

/**
 * Created by Long Nguyen on 12/4/14.
 */
public class ProductService {

    private IProductDAO productDAO = new ProductDao();

    public Collection<Product> findAll() {
        return productDAO.findAll();
    }

    public boolean create(Product product) {
        // Do something with product object
        try {
            productDAO.save(product);
            return true;
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmpty() {
        Collection<Product> products = productDAO.findAll();
        return (!(products!= null &&!products.isEmpty()));
    }


}
