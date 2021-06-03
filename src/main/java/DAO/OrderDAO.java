package DAO;

import Model.Client;
import Model.Orders;

import java.util.List;
/**
 * This class access data for Order type objects
 */
public class OrderDAO extends AbstractDAO<Orders> {
    /**
     * Find orders in database by id
     * @param id The id
     * @return List of clients
     */
    public List<Orders> findById(int id){
        return super.findById(id);
    }
    /**
     * Insert an orders into database
     * @param order The order to be inserted
     * @return An int
     */
    public int insert (Orders order){
        return super.insert(order);
    }
    /**
     * Delete an orders form database
     * @param id The id
     * @return The order that was deleted
     */
    public Orders delete(int id){
        return super.delete(id);
    }
    /**
     * Find all orders in database
     * @return List of orders from database
     */
    public List<Orders> findAll(){
        return super.findAll();
    }
}
