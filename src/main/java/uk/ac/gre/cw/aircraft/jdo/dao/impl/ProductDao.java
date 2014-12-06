package uk.ac.gre.cw.aircraft.jdo.dao.impl;

import uk.ac.gre.cw.aircraft.jdo.dao.AbstractDAO;
import uk.ac.gre.cw.aircraft.jdo.dao.IProductDAO;
import uk.ac.gre.cw.aircraft.jdo.entities.Product;

/**
 * Created by Long Nguyen on 12/4/14.
 */
public class ProductDao extends AbstractDAO<Product, Long> implements IProductDAO {
    public ProductDao() {
        super(Product.class);
    }
}
