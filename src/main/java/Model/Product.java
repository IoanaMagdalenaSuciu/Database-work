package Model;


/**
 * This class model product objects
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private int cant;
    /**
     * Empty class constructor
     */
    public Product(){}

    /**
     * Class constructor
     * @param id id
     * @param name Product name
     * @param pret Product price
     * @param cant Quantity of product
     */
    public Product(int id, String name, float pret, int cant)
    {
        this.id = id;
        this.name = name;
        this.price = pret;
        this.cant = cant;
    }

    /**
     * Class constructor
     * @param name Product name
     * @param pret Product price
     * @param cant Quantity of product
     */
    public Product(String name, float pret, int cant)
    {
        super();
        this.name = name;
        this.price = pret;
        this.cant = cant;
    }

    /**
     *Get product id
     * @return Product id
     */
    public int getId() {
        return id;
    }

    /**
     * Set product id
     * @param id Product new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get product name
     * @return Product name
     */
    public String getName() {
        return name;
    }

    /**
     * Set product name
     * @param name New product's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get product's price
     * @return Product's price
     */
    public float getPrice(){return (float) price;}

    /**
     * Set product's price
     * @param price New products' price
     */
    public void setPrice(float price){this.price = price;}

    /**
     * Get quantity of product
     * @return Quentity of product
     */
    public int getCant(){return cant;}

    /**
     * Set quantity of product
     * @param cant New quantity of product
     */
    public void setCant(int cant){this.cant = cant;}

}
