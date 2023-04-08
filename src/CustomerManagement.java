import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerManagement extends JFrame {
    private JButton newButton;
    private JButton cancelButton;
    private JButton addNewCustomerButton;
    private JTextField nameTextField;
    private JPanel mainPanel;
    private JLabel userManagementLabel;
    private JTextField addressTextField;
    private JTextField dateOfBirthTextField;
    private JTextField dayJoinedTextField;
    private JComboBox typeComboBox;
    private JLabel CopyrightLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel dateOfBirthLabel;
    private JLabel dayJoinedLabel;
    private JLabel infoLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTable table1;
    private JButton updateButton;
    private JTextField emailTextField;
    private JComboBox agentIDComboBox;
    private JLabel agentIDLabel;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public CustomerManagement() {
        setContentPane(mainPanel);
        setTitle("ATS System");
        setSize(600, 600);
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket
        update_AgentComboBox();

        dateOfBirthTextField.setText("YYYY-MM-DD");
        dayJoinedTextField.setText("YYYY-MM-DD");

        // CANCEL
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });

        // ADD (SAVE)
        addNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dispose();
                //AddCustomer addCustomer = new AddCustomer();

                String customerName, customerType, customerAddress, customerEmail, customerDateOfBirth, customerDayJoined, agentID;

                customerName = nameTextField.getText();
                customerType = typeComboBox.getSelectedItem().toString();
                customerAddress = addressTextField.getText();
                customerEmail = emailTextField.getText();
                customerDateOfBirth = dateOfBirthTextField.getText();
                customerDayJoined = dayJoinedTextField.getText();
                agentID = agentIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("insert into Ticket(customerName, customerType, customerAddress, customerEmail, customerDateOfBirth, customerDayJoined, agentID)values(?,?,?,?,?,?,?)");
                    pst.setString(1, customerName);
                    pst.setString(2, customerType);
                    pst.setString(3, customerAddress);
                    pst.setString(4, customerEmail);
                    pst.setString(5, customerDateOfBirth);
                    pst.setString(6, customerDayJoined);
                    pst.setString(7, agentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    typeComboBox.setSelectedIndex(0);
                    nameTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    dateOfBirthTextField.setText("YYYY-MM-DD");
                    dayJoinedTextField.setText("YYYY-MM-DD");
                    agentIDComboBox.setSelectedIndex(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // SEARCH
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String customerID = searchTextField.getText();

                    pst = main.con.prepareStatement("select customerID, customerName, customerType, customerAddress, customerEmail, customerDateOfBirth, customerDayJoined, agentID from Customer where customerID = ?");
                    pst.setString(1, customerID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String customerName = rs.getString(2);
                        String customerType = rs.getString(3);
                        String customerAddress = rs.getString(4);
                        String customerEmail = rs.getString(5);
                        String customerDateOfBirth = rs.getString(6);
                        String customerDayJoined = rs.getString(7);
                        String agentID = rs.getString(8);

                        nameTextField.setText(customerName);
                        typeComboBox.setSelectedItem(customerType);
                        addressTextField.setText(customerAddress);
                        emailTextField.setText(customerEmail);
                        dateOfBirthTextField.setText(customerDateOfBirth);
                        dayJoinedTextField.setText(customerDayJoined);
                        agentIDComboBox.setSelectedItem(agentID);
                    } else {
                        typeComboBox.setSelectedIndex(0);
                        nameTextField.setText("");
                        addressTextField.setText("");
                        emailTextField.setText("");
                        dateOfBirthTextField.setText("YYYY-MM-DD");
                        dayJoinedTextField.setText("YYYY-MM-DD");
                        agentIDComboBox.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerID, customerName, customerType, customerAddress, customerEmail, customerDateOfBirth, customerDayJoined, agentID;

                customerID = searchTextField.getText();
                customerName = nameTextField.getText();
                customerType = typeComboBox.getSelectedItem().toString();
                customerAddress = addressTextField.getText();
                customerEmail = emailTextField.getText();
                customerDateOfBirth = dateOfBirthTextField.getText();
                customerDayJoined = dayJoinedTextField.getText();
                agentID = agentIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("update Customer set customerName = ?, customerType = ?, customerAddress = ?, customerEmail = ?, customerDateOfBirth = ?, customerDayJoined = ?, agentID = ? where customerID = ?");
                    pst.setString(1, customerName);
                    pst.setString(2, customerType);
                    pst.setString(3, customerAddress);
                    pst.setString(4, customerEmail);
                    pst.setString(5, customerDateOfBirth);
                    pst.setString(6, customerDayJoined);
                    pst.setString(7, agentID);
                    pst.setString(8, customerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    nameTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    dateOfBirthTextField.setText("YYYY-MM-DD");
                    dayJoinedTextField.setText("YYYY-MM-DD");
                    agentIDComboBox.setSelectedIndex(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // DELETE
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerID;
                customerID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Customer where customerID = ?");
                    pst.setString(1,customerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    searchTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    nameTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    dateOfBirthTextField.setText("YYYY-MM-DD");
                    dayJoinedTextField.setText("YYYY-MM-DD");
                    agentIDComboBox.setSelectedIndex(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Customer");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update_AgentComboBox() {
        try {
            pst = main.con.prepareStatement("select * from Travel_Agent");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                agentIDComboBox.addItem(rs.getString("agentID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
