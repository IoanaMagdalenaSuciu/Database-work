package Presentation;

import BLL.ClientBLL;

import BLL.OrderBLL;
import BLL.ProductBLL;
import Model.Client;
import Model.Orders;
import Model.Product;
import com.itextpdf.text.DocumentException;

import start.ClientTable;
import start.FileWriter;
import start.OrderTable;
import start.ProductTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * This class control the application
 */
public class Controller {

    /**
     * Class constructor
     */
    public Controller() {
        FileWriter file = new FileWriter();
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        OrderBLL orderBLL = new OrderBLL();
        ProductGUI productGUI = new ProductGUI(productBLL);
        OrderGUI orderGUI = new OrderGUI(clientBLL,productBLL,orderBLL,file);
        ClientGUI clientGUI = new ClientGUI(clientBLL) ;

        ProductTable productTable = new ProductTable();
        OrderTable orderTable = new OrderTable();
        ClientTable clienTable = new ClientTable();
    }

    /**
     * The main method
     * @param args No argument needed
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //delete all from tables
        Controller c = new Controller();
    }
}




