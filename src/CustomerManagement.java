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
    private JTextField textField1;
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

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });
        addNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddCustomer addCustomer = new AddCustomer();
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
}
