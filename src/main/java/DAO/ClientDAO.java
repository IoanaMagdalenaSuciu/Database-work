package DAO;

import Model.Client;

import java.sql.*;
import java.util.List;

/**
 * This class access data for Client type objects
 */
public class ClientDAO extends AbstractDAO<Client> {
    /**
     * Find clients in database by id
     * @param id The id
     * @return List of clients
     */
    public List<Client> findById(int id){
        return super.findById(id);
    }

    /**
     * Find clients in database by name
     * @param name String
     * @return The clint with specified name
     */
    public Client findByName(String name){return super.findByName(name);}

    /**
     * Insert a client into database
     * @param client The client to be inserted
     * @return An int
     */
    public int insert (Client client){
        return super.insert(client);
    }

    /**
     * Delete a client form database
     * @param id The id
     * @return The client that was deleted
     */
    public Client delete(int id){
        return super.delete(id);
    }

    /**
     * Find all clients in database
     * @return List of clients from database
     */
    public List<Client> findAll(){
        return super.findAll();
    }


}
