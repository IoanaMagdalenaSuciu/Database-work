package start;

import Model.Product;

import java.util.List;
/**
 * This class compute the header for the table in ProductGUI
 */
public class ProductTable extends TableDAO<Product> {
    /**
     * Compute the String of fields of Client
     * @return The header for table
     */
    public List<String> retrieveInfo() {
        return super.retrieveInfo();
    }
}
