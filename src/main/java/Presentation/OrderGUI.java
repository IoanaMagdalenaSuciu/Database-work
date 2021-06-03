package Presentation;

import BLL.ClientBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
import Model.Orders;
import Model.Product;
import com.itextpdf.text.DocumentException;
import start.FileWriter;
import start.OrderTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
/**
 * Creates the graphical interface for Order
 */
public class OrderGUI extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JLabel title = new JLabel("Order");
    JButton delete = new JButton("Delete");
    JButton insert = new JButton("Insert");
    JButton update = new JButton("Update");
    JButton view = new JButton("View");
    JTable orders  = new JTable() ;
    OrderBLL orderBLL ;
    OrderTable orderTable = new OrderTable();
    JLabel nameClient = new JLabel("Name Client");
    JLabel nameProduct = new  JLabel("Name Product");
    JLabel cant = new  JLabel("Quantity");
    JTextField nameClientField = new JTextField();
    JTextField nameProductField = new JTextField();
    JTextField cantField = new JTextField();
    JButton find = new JButton("Find By ID");
    JTextField idText = new JTextField();
    ClientBLL clientBLL;
    ProductBLL productBLL;
    FileWriter file;
    JButton invoice = new JButton("Invoice");

    /**
     * Class constructor
     * @param clientBLL Client operations
     * @param productBLL Product operations
     * @param orderBLL Order operations
     * @param file For generating bill
     */
    public OrderGUI(ClientBLL clientBLL, ProductBLL productBLL, OrderBLL orderBLL, FileWriter file)
    {
        this.file = file;
        this.orderBLL = orderBLL;
        this.productBLL = productBLL;
        this.clientBLL = clientBLL;
        mainPanel.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,800, 550);
        this.setTitle("Simulator");
        this.setResizable(true);
        //this.setSize();
        this.setVisible(true);
        this.setLayout(null);
        //  updateTable();
        title.setForeground(new Color(0xD6AD60));
        title.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
        title.setOpaque(true);
        title.setHorizontalAlignment((JLabel.CENTER));
        title.setBounds(230,25,250,55);
        title.setBackground(new Color(0x122620));

        delete.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        delete.setBackground(new Color(0x122620));
        delete.setBounds(10,350,150,50);
        delete.setForeground(new Color(0xD6AD60));
        delete.addActionListener( this);

        insert.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        insert.setBackground(new Color(0x122620));
        insert.setBounds(170,350,150,50);
        insert.setForeground(new Color(0xD6AD60));
        insert.addActionListener( this);


        view.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        view.setBackground(new Color(0x122620));
        view.setBounds(330,350,150,50);
        view.setForeground(new Color(0xD6AD60));
        view.addActionListener( this);

        invoice.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        invoice.setBackground(new Color(0x122620));
        invoice.setBounds(360,480,150,30);
        invoice.setForeground(new Color(0xD6AD60));
        invoice.addActionListener( this);

        update.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        update.setBackground(new Color(0x122620));
        update.setBounds( 490, 350,150,50);
        update.setForeground(new Color(0xD6AD60));
        update.addActionListener( this);

        find.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        find.setBackground(new Color(0x122620));
        find.setBounds(360,420,150,50);
        find.setForeground(new Color(0xD6AD60));
        find.addActionListener( this);

        idText.setFont(new Font("Comic Sans",Font.ITALIC, 30));
        idText.setBounds(540,420,200,40);

        nameClient.setFont(new Font(Font.SERIF, Font.PLAIN | Font.BOLD, 17));
        nameClient.setOpaque(true);
        nameClient.setBounds(10,410,100,30);
        nameClient.setBackground(new Color(0x122620));
        nameClient.setForeground(new Color(0xD6AD60));


        nameClientField.setFont(new Font("Comic Sans",Font.ITALIC, 15));
        nameClientField.setBounds(150,410,200,20);

        nameProduct.setFont(new Font(Font.SERIF, Font.PLAIN | Font.BOLD, 17));
        nameProduct.setOpaque(true);
        nameProduct.setBounds(10,440,120,30);
        nameProduct.setBackground(new Color(0x122620));
        nameProduct.setForeground(new Color(0xD6AD60));

        nameProductField.setFont(new Font("Comic Sans",Font.ITALIC, 15));
        nameProductField.setBounds(150,440,200,20);

        cant.setFont(new Font(Font.SERIF, Font.PLAIN | Font.BOLD, 17));
        cant.setOpaque(true);
        cant.setBounds(10,470,100,20);
        cant.setBackground(new Color(0x122620));
        cant.setForeground(new Color(0xD6AD60));

        cantField.setFont(new Font("Comic Sans",Font.ITALIC, 15));
        cantField.setBounds(150,470,200,20);

        mainPanel.add(invoice);
        mainPanel.add(find);
        mainPanel.add(update);
        mainPanel.add(insert);
        mainPanel.add(title);
        mainPanel.add(delete);
        mainPanel.add(view);
        mainPanel.setSize(900, 700);
        mainPanel.setBackground(new Color(0x122620));

        mainPanel.add(nameProductField);
        mainPanel.add(nameClient);
        mainPanel.add(nameClientField);
        mainPanel.add(nameProduct);
        mainPanel.add(cantField);
        mainPanel.add(cant);
        mainPanel.add(idText);
        this.add(mainPanel);
    }

    /**
     * Update the table of orders
     * @param model Table model
     */
    public void updateTable(DefaultTableModel model){

        Component[] componentList = mainPanel.getComponents();
        for(Component c : componentList){
            if(c instanceof JScrollPane){
                mainPanel.remove(c);
            }
        }
        mainPanel.revalidate();
        mainPanel.repaint();

        orders = new JTable(model);
        orders.setRowSelectionAllowed(true);
        orders.setFillsViewportHeight(true);
        JScrollPane scroll= new JScrollPane(orders);
        scroll.setBounds(70,100,600,200);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVisible(true);
        mainPanel.add(scroll);

        this.setVisible(true);


    }

    /**
     * Performe actions
     * @param e Action event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            int row = orders.getSelectedRow();
            int id = Integer.parseInt(orders.getValueAt(row,0).toString());
            System.out.println(id);
            orderBLL.deleteOrder(id);
            updateTable(orderTable.retrieveInfo(orderBLL.findALl()) );
        }
        if (e.getSource() == insert) {

            if(clientBLL.findByName(nameClientField.getText()) != null ){
                if (productBLL.findByName(nameProductField.getText()) != null){
                    if (productBLL.findByName(nameProductField.getText()).getCant() != 0){
                        Orders order = new Orders(nameClientField.getText(), nameProductField.getText(),Integer.parseInt(cantField.getText()));
                        Product product = productBLL.findByName(nameProductField.getText());
                        product.setCant(product.getCant()-1);
                        productBLL.updateProduct(product);
                        if(orderBLL.insertOrder(order) != 0){
                            updateTable( orderTable.retrieveInfo(orderBLL.findALl()));
                            /*try {
                                file.generateBill(order);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            } catch (DocumentException documentException) {
                                documentException.printStackTrace();
                            }*/
                        }else{
                            JOptionPane.showMessageDialog(this, "The order could not be added to the database ","Error", JOptionPane.WARNING_MESSAGE);
                        }

                    }else{

                        JOptionPane.showMessageDialog(this, "Insufficient stock ","Error", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "The client with name = " + nameProductField.getText() + " was not found!","Error", JOptionPane.WARNING_MESSAGE);
                }
            }else{

                JOptionPane.showMessageDialog(this, "The client with name = " + nameClientField.getText() + " was not found!","Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == view) {
            updateTable(orderTable.retrieveInfo(orderBLL.findALl()));
        }
        if (e.getSource() == update){
            int row = orders.getSelectedRow();
            Orders order =  new Orders(Integer.parseInt(orders.getValueAt(row,0).toString()), orders.getValueAt(row, 1).toString(),orders.getValueAt(row, 2).toString(),Integer.parseInt(orders.getValueAt(row, 3).toString()));

            System.out.println(order);
            if(orderBLL.updateOrder(order) != 0){
                updateTable( orderTable.retrieveInfo(orderBLL.findALl()));
            }else{
                JOptionPane.showMessageDialog(this, " Could not update order ","Error", JOptionPane.WARNING_MESSAGE);

            }
        }
        if (e.getSource() == find) {

            if (orderBLL.findOrderById(Integer.parseInt(idText.getText())) != null) {
                updateTable(orderTable.retrieveInfo(orderBLL.findOrderById(Integer.parseInt(idText.getText()))));
            } else {
                JOptionPane.showMessageDialog(this, "The order with id =" + Integer.parseInt(idText.getText()) + " was not found!","Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == invoice) {

            int row = orders.getSelectedRow();
            try {
                file.generateBill(orderBLL.findOrderById(Integer.parseInt(orders.getValueAt(row,0).toString())).get(0));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (DocumentException documentException) {
                documentException.printStackTrace();
            }
        }

    }


}
