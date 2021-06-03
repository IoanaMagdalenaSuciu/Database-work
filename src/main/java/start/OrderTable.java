package start;

import Model.Orders;

import java.util.List;
/**
 * This class compute the header for the table in OrderGUI
 */
public class OrderTable extends TableDAO<Orders> {
    /**
     * Compute the String of fields of Order
     * @return The header for table
     */
    public List<String> retrieveInfo() {
        return super.retrieveInfo();
    }
}
