package start;

import Model.Orders;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 * This class compute the header for the table in OrderGUI
 */
public class OrderTable extends TableDAO<Orders> {
    /**
     * Compute the String of fields of Order
     * @return The table model
     */
    public DefaultTableModel retrieveInfo(List<Orders> listObj) {
        return super.retrieveInfo(listObj);

    }
}
