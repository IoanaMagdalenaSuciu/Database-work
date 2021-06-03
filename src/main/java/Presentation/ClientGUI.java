package Presentation;

import BLL.ClientBLL;

import Model.Client;
import start.ClientTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Creates the graphical interface for Client
 */
public class ClientGUI extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JLabel title = new JLabel("Client");
    JButton delete = new JButton("Delete");
    JButton insert = new JButton("Insert");
    JButton update = new JButton("Update");
    JButton view = new JButton("View");
    JTable clients = new JTable() ;
    ClientBLL clientBLL;
    ClientTable clienTable = new ClientTable();
    JLabel name = new JLabel("Name");
    JLabel email = new  JLabel("Email");
    JLabel address = new  JLabel("Address");
    JTextField nameField = new JTextField();
    JTextField emailFiled = new JTextField();
    JTextField addressField = new JTextField();
    JButton find = new JButton("Find By ID");
    JTextField idText = new JTextField();

    /**
     * Class constructor
     * @param client Object for logic operations
     */
    public ClientGUI( ClientBLL client)
    {
        clientBLL = client;
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

        email.setFont(new Font(Font.SERIF, Font.PLAIN | Font.BOLD, 20));
        email.setOpaque(true);
        email.setBounds(10,440,80,20);
        email.setBackground(new Color(0x122620));
        email.setForeground(new Color(0xD6AD60));

        emailFiled.setFont(new Font("Comic Sans",Font.ITALIC, 15));
        emailFiled.setBounds(100,440,200,20);

        address.setFont(new Font(Font.SERIF, Font.PLAIN | Font.BOLD, 20));
        address.setOpaque(true);
        address.setBounds(10,470,80,20);
        address.setBackground(new Color(0x122620));
        address.setForeground(new Color(0xD6AD60));

        addressField.setFont(new Font("Comic Sans",Font.ITALIC, 15));
        addressField.setBounds(100,470,200,20);

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
        mainPanel.add(addressField);
        mainPanel.add(address);
        mainPanel.add(emailFiled);
        mainPanel.add(email);
        mainPanel.add(idText);
        this.add(mainPanel);
    }

    /**
     * Update the table of clients
     * @param model The table model
     *
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

        clients = new JTable(model);
       // clients.setBackground(new Color(0xD6AD60));
        clients.setRowSelectionAllowed(true);
        clients.setFillsViewportHeight(true);
        JScrollPane scroll= new JScrollPane(clients);
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
            int row = clients.getSelectedRow();
            int id = Integer.parseInt(clients.getValueAt(row, 0).toString());
            System.out.println(id);
            clientBLL.deleteClient(id);
            updateTable(clienTable.retrieveInfo(clientBLL.getClients(clientBLL.findALl()), clients));

        }
        if (e.getSource() ==insert) {
            Client client = new Client(nameField.getText(), emailFiled.getText(), addressField.getText());

            if (clientBLL.insertClient(client) != 0) {
                updateTable(clienTable.retrieveInfo(clientBLL.getClients(clientBLL.findALl()),clients));
            } else {
                JOptionPane.showMessageDialog(this, "The client could not be added to the database ", "Error", JOptionPane.WARNING_MESSAGE);

            }
        }
        if (e.getSource() == view) {
            updateTable(clienTable.retrieveInfo(clientBLL.getClients(clientBLL.findALl()),clients) );
        }
        if (e.getSource() ==  update) {
            int row = clients.getSelectedRow();
            Client client = new Client(Integer.parseInt(clients.getValueAt(row, 0).toString()),clients.getValueAt(row, 1).toString(), clients.getValueAt(row, 2).toString(), clients.getValueAt(row, 3).toString());

            System.out.println(client);
            if (clientBLL.updateClient(client) != 0) {
                 updateTable(clienTable.retrieveInfo(clientBLL.getClients(clientBLL.findALl()),clients));
            } else {
                JOptionPane.showMessageDialog(this, " Could not update client ", "Error", JOptionPane.WARNING_MESSAGE);

            }
        }
        if (e.getSource() ==  find) {

            if (clientBLL.findClientById(Integer.parseInt( idText.getText())) != null) {
                 updateTable( clienTable.retrieveInfo(clientBLL.getClients(clientBLL.findClientById(Integer.parseInt( idText.getText()))),clients));
            } else {
                JOptionPane.showMessageDialog(this, "The client with id =" + Integer.parseInt(idText.getText()) + " was not found!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

    }



}
