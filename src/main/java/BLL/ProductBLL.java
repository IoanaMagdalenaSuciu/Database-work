package BLL;

import DAO.ProductDAO;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * The class that will implement the logic for the Order type objects
 */
public class ProductBLL {
    /**
     * The object through which the connection to the database will be made
     */
    private ProductDAO productDAO;
    /**
     * Class constructor
     */
    public ProductBLL(){
        productDAO = new ProductDAO();
    }

    /**
     * Search products by id
     * @param id the id
     * @return the products with the corresponding id
     */
    public List<Product> findProductById(int id) {
        List<Product> st = productDAO.findById(id);
        if (st == null) {
           return null;//throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Insert new product
     * @param product The product to be inserted
     * @return a number  greater than 0 if the client has been inserted
     */
    public int insertProduct(Product product) {
        return productDAO.insert(product);
    }

    /**
     * Update a product
     * @param product Product to be updated
     * @return a number greater than 0 if the client has been updated
     */
    public int updateProduct(Product product){
        return productDAO.update(product);
    }

    /**
     * Find all products
     * @return The list of products
     */
    public List<Product> findALl(){
        return productDAO.findAll();
    }

    /**
     * Delete a Product
     * @param id The id of product to be deleted
     */
    public void deleteProduct(int id){
        productDAO.delete(id);
    }

    /**
     * Convert a list of products to a matrix of objects
     * @param products list of products
     * @return matric of objects
     */
    public Object[][] getProducts(List<Product> products){
        Object[][] result = new Object[products.size()][];
        int i = 0;
        for(Product product: products){
            result[i] = new Object[]{product.getId(), product.getName(),product.getPrice(),product.getCant()};
            i++;

        }
        return result;
    }

    /**
     * Search a product by name
     * @param name The name
     * @return The products with corresponding name
     */
    public Product findByName(String name){
        Product st = productDAO.findByName(name);
        if (st == null) {
            return null;
        }
        return st;
    }

}
