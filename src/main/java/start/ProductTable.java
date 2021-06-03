package start;

import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 * This class compute the header for the table in ProductGUI
 */
public class ProductTable extends TableDAO<Product> {
    /**
     * Compute the String of fields of Client
     * @return The header for table
     */
    public DefaultTableModel retrieveInfo(List<Product> objectList) {
        return super.retrieveInfo(objectList);
    }
}
