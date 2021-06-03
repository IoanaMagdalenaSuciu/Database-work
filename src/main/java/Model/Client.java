package Model;

/**
 * This class models client objects
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;

    /**
     * Empty class constructor
     */
    public Client(){
    }

    /**
     * Class constructor
     * @param id id
     * @param name Client name
     * @param email Client email
     * @param address Client address
     */
    public Client (int id, String name, String email, String address)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;

    }

    /**
     * Class constructor
     * @param name  Client name
     * @param email Client email
     * @param address Client address
     */
    public Client (String name, String email, String address)
    {
      //  super();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Get client id
     * @return Client id
     */
    public int getId() {
        return id;
    }

    /**
     * Set client id
     * @param id The new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get client name
     * @return The client name
     */
    public String getName() {
        return name;
    }

    /**
     * Set client name
     * @param name new client name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get client address
     * @return Client address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set client address
     * @param address new client address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get client email
     * @return Client's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set Client email
     * @param email new client email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Transpose client information into a string
     * @return client's information
     */
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + "]";
    }

}
