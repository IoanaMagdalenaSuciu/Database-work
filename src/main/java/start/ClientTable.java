package start;

import Model.Client;
import Model.Orders;
/**
 * This class compute the header for the table in ClientGUI
 */
import java.util.List;
public class ClientTable extends TableDAO<Client> {
    /**
     * Compute the String of fields of Client
     * @return The header for table
     */
    public List<String> retrieveInfo() {
        return super.retrieveInfo();
    }
}
