package start;

import Model.Client;
import Model.Orders;
/**
 * This class compute the header for the table in ClientGUI
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
public class ClientTable extends TableDAO<Client> {
    /**
     * Compute the String of fields of Client
     * @return The header for table
     */
    public DefaultTableModel retrieveInfo(List<Client> objectList)  {
       return super.retrieveInfo(objectList);

    }
}
