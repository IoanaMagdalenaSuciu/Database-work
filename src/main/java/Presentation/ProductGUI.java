package Presentation;

import BLL.ClientBLL;
import BLL.ProductBLL;
import Model.Client;
import Model.Product;

import start.ProductTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Creates the graphical interface for Product
 */
public   class ProductGUI extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JLabel title = new JLabel("Product");
    JButton delete = new JButton("Delete");
    JButton insert = new JButton("Insert");
    JButton update = new JButton("Update");
    JButton view = new JButton("View");
    JTable products ;
    ProductBLL productBLL;
    ProductTable productTable = new ProductTable();
    JLabel name = new JLabel("Name");
    JLabel price = new  JLabel("Price");
    JLabel cant = new  JLabel("Quantity");
    JTextField nameField = new JTextField();
    JTextField priceField = new JTextField();
    JTextField cantField = new JTextField();
    JButton find = new JButton("Find By ID");
    JTextField idText = new JTextField();

    /**
     * Class constructor
     * @param productBLL Object for logic operations
     */
    public ProductGUI(ProductBLL productBLL )
    {
        this.productBLL = productBLL;
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

        update.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        update.setBackground(new Color(0x122620));
        update.setBounds(490,350,150,50);
        update.setForeground(new Color(0xD6AD60));
        update.addActionListener( this);

        find.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        find.setBackground(new Color(0x122620));
        find.setBounds(320,420,150,50);
        find.setForeground(new Color(0xD6AD60));
        find.addActionListener( this);

        idText.setFont(new Font("Comic Sans",Font.ITALIC, 30));
        idText.setBounds(490,420,200,40);

        name.setFont(new Font(Font.SERIF, Font.PLAIN | Font.BOLD, 20));
        name.setOpaque(true);
        name.setBounds(10,410,50,20);
        name.setBackground(new Color(0x122620));
        name.setForeground(new Color(0xD6AD60));


        nameField.setFont(new Font("Comic Sans",Font.ITALIC, 15));
        nameField.setBounds(100,410,200,20);

        price.setFont(new Font(Font.SERIF, Font.PLAIN | Font.BOLD, 20));
        price.setOpaque(true);
        price.setBounds(10,440,80,20);
        price.setBackground(new Color(0x122620));
        price.setForeground(new Color(0xD6AD60));

        priceField.setFont(new Font("Comic Sans",Font.ITALIC, 15));
        priceField.setBounds(100,440,200,20);

        cant.setFont(new Font(Font.SERIF, Font.PLAIN | Font.BOLD, 20));
        cant.setOpaque(true);
        cant.setBounds(10,470,80,20);
        cant.setBackground(new Color(0x122620));
        cant.setForeground(new Color(0xD6AD60));

        cantField.setFont(new Font("Comic Sans",Font.ITALIC, 15));
        cantField.setBounds(100,470,200,20);

        mainPanel.add(find);
        mainPanel.add(update);
        mainPanel.add(insert);
        mainPanel.add(title);
        mainPanel.add(delete);
        mainPanel.add(view);
        mainPanel.setSize(900, 700);
        mainPanel.setBackground(new Color(0x122620));

        mainPanel.add(name);
        mainPanel.add(nameField);
        mainPanel.add(price);
        mainPanel.add(priceField);
        mainPanel.add(cantField);
        mainPanel.add(cant);
        mainPanel.add(idText);
        this.add(mainPanel);
    }

    /**
     * Update table of products
     * @param data Products data
     * @param header The header of the table that contains the name of the product data
     */
    public void updateTable(java.util.List<Object[]> data, List<String> header){

        Component[] componentList = mainPanel.getComponents();
        for(Component c : componentList){
            if(c instanceof JScrollPane){
                mainPanel.remove(c);
            }
        }
        mainPanel.revalidate();
        mainPanel.repaint();

        products = new JTable(data.toArray(new Object[][]{}), header.toArray(new String[]{}));
        // clients.setBackground(new Color(0xD6AD60));
        products.setRowSelectionAllowed(true);
        products.setFillsViewportHeight(true);
        JScrollPane scroll= new JScrollPane(products);
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
            int row = products.getSelectedRow();
            int id = Integer.parseInt(products.getValueAt(row,0).toString());
            System.out.println(id);
            productBLL.deleteProduct(id);
            updateTable(productBLL.getProducts(productBLL.findALl()), productTable.retrieveInfo());
        }
        if (e.getSource() == insert) {
            Product product = new Product(nameField.getText(), Float.parseFloat(priceField.getText()),Integer.parseInt(cantField.getText()));

            if(productBLL.insertProduct(product) != 0){
                updateTable(productBLL.getProducts(productBLL.findALl()), productTable.retrieveInfo());
            }else{
                JOptionPane.showMessageDialog(this, "The product could not be added to the database ","Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == view) {
            updateTable(productBLL.getProducts(productBLL.findALl()), productTable.retrieveInfo());
        }
        if (e.getSource() == update){
            int row = products.getSelectedRow();
            // service.updateInMenu(Float.parseFloat(menu.getValueAt(row,1).toString()),menu.getValueAt(row,0).toString());
            Product product =  new Product(Integer.parseInt(products.getValueAt(row,0).toString()), products.getValueAt(row, 1).toString(),Float.parseFloat(products.getValueAt(row, 2).toString()),Integer.parseInt(products.getValueAt(row, 3).toString()));

            System.out.println(product);
            if(productBLL.updateProduct(product) != 0){
                updateTable(productBLL.getProducts(productBLL.findALl()), productTable.retrieveInfo());
            }else{
                JOptionPane.showMessageDialog(this, "Could not update product ","Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == find) {
            if (productBLL.findProductById(Integer.parseInt(idText.getText())) != null) {
                updateTable(productBLL.getProducts(productBLL.findProductById(Integer.parseInt(idText.getText()))), productTable.retrieveInfo());
            } else {
                JOptionPane.showMessageDialog(this, "The product with id =" + Integer.parseInt(idText.getText()) + " was not found!","Error", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

}
