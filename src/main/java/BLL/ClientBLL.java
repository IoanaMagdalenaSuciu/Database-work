package BLL;

import BLL.Validators.EmailValidator;
import BLL.Validators.Validator;
import DAO.ClientDAO;
import Model.Client;


import java.util.ArrayList;
import java.util.List;

/**
 * The class that will implement the logic for the Client type objects
 */

public class ClientBLL {
    /**
     * List of validators
     */
    private List<Validator<Client>> validators;
    /**
     * The object through which the connection to the database will be made
     */
    private ClientDAO clientDAO;

    /**
     * Class constructor
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * Search  clients by id
     * @param id the id
     * @return the clients with the corresponding id
     */
    public  List<Client> findClientById(int id) {
        List<Client> st = clientDAO.findById(id);
        if (st == null) {
            return null;
        }
        return st;
    }

    /**
     * Search  clients by name
     * @param name the name
     * @return the clients with corresponding name
     */
    public Client findByName(String name){
        Client st = clientDAO.findByName(name);
        if (st == null) {
            return null;
        }
        return st;
    }

    /**
     * Insert new client
     * @param client Client to be inserted
     * @return a number  greater than 0 if the client has been inserted
     */
    public int insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return clientDAO.insert(client);
    }

    /**
     * Update a client
     * @param client Client to be updated
     * @return a number greater than 0 if the client has been updated
     */
    public int updateClient(Client client){
        for (Validator<Client> v: validators){
            v.validate(client);
        }
        return clientDAO.update(client);
    }

    /**
     * Delete a Client
     * @param id The id of client to be deleted
     */
    public void deleteClient(int id){
        clientDAO.delete(id);
    }

    /**
     * Find all clients
     * @return The list of clients
     */
    public List<Client> findALl(){
        return clientDAO.findAll();
    }

    /**
     * Convert a list of clients to a matrix of objects
     * @param clients list of clients
     * @return matrix of objects
     */
    public List<Object[]> getClients(List<Client> clients){
        List<Object[]> result = new ArrayList<>();
        for(Client client: clients){
            result.add(new Object[]{client.getId(), client.getName(),client.getEmail(),client.getAddress()});
        }
        return result;
    }

}
