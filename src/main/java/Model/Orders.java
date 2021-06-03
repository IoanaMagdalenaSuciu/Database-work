package Model;

public class Orders {
    private int id;
    private String clientName;
    private String productName;
    private int cant;

    public Orders(){}
    public Orders(int id, String clientName, String productName, int cant)
    {
        this.id = id;
        this.clientName = clientName;
        this.productName = productName;
        this.cant = cant;
    }
    public Orders(String clientName, String productName, int cant)
    {
        super();
        this.clientName = clientName;
        this.productName = productName;
        this.cant = cant;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getClientName() {
        return clientName;
    }
    public void setClientName(String name) {
        this.clientName = name;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String name) {
        this.productName = name;
    }
    public int getCant(){return cant;}
    public void setCant(int cant){this.cant = cant;}
    public String toString(){
        String result ="";
        result = "Order number "+this.id+ " Client name " + this.clientName + "  Product: "+ this.productName + " quantity:  "+ this.cant;
        return result;
    }






}
