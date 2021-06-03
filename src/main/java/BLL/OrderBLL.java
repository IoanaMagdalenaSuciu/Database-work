package BLL;


import DAO.OrderDAO;
import Model.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The class that will implement the logic for the Order type objects
 */
public class OrderBLL {
    /**
     * The object through which the connection to the database will be made
     */
    private OrderDAO orderDAO;
    /**
     * Class constructor
     */
    public OrderBLL(){
        orderDAO = new OrderDAO();
    }

    /**
     * Search orders by id
     * @param id the id
     * @return the orders with the corresponding id
     */
    public List<Orders> findOrderById(int id) {
        List<Orders> st = orderDAO.findById(id);
        if (st == null) {
          return null; // throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Insert new order
     * @param order Order to be inserted
     * @return a number  greater than 0 if the order has been inserted
     */
    public int insertOrder(Orders order) {
        return orderDAO.insert(order);
    }

    /**
     * Update an order
     * @param order Order to be updated
     * @return a number greater than 0 if the client has been updated
     */
    public int updateOrder(Orders order){

        return orderDAO.update(order);
    }

    /**
     * elete an Order
     * @param id The id of order to be deleted
     */
    public void deleteOrder(int id){
        orderDAO.delete(id);
    }

    /**
     * Find all orders
     * @return The list of orders
     */
    public List<Orders> findALl(){
        return orderDAO.findAll();
    }
    /**
     * Convert a list of orders to a matrix of objects
     * @param orders list of orders
     * @return matrix of objects
     */
    public Object[][] getOrder(List<Orders> orders){
        Object[][] result = new Object[orders.size()][];
        int i = 0;
        for(Orders order: orders){
            result[i] = (new Object[]{order.getId(), order.getClientName(),order.getProductName(),order.getCant()});
            i++;
        }
        return result;
    }



}
