package DAO;

import Model.Client;
import Model.Orders;
import Model.Product;

import java.util.List;
/**
 * This class access data for Order type objects
 */
public class ProductDAO extends AbstractDAO<Product>{
    /**
     * Find products in database by id
     * @param id The id
     * @return List of products
     */
    public List<Product> findById(int id){
        return super.findById(id);
    }
    /**
     * Find products in database by name
     * @param name String
     * @return The product with specified name
     */
    public Product findByName(String name){return super.findByName(name);}
    /**
     * Insert a product into database
     * @param product The product to be inserted
     * @return An int
     */
    public int insert (Product product){
        return super.insert(product);
    }
    /**
     * Delete a product form database
     * @param id The id
     * @return The product that was deleted
     */
    public Product delete(int id){
        return super.delete(id);
    }
    /**
     * Find all products in database
     * @return List of products from database
     */
    public List<Product> findAll(){
        return super.findAll();
    }

}
