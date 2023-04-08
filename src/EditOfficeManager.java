import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditOfficeManager extends JFrame {
    private JPanel mainPanel;
    private JLabel stockControlLabel;
    private JLabel managerNameLabel;
    private JLabel usernameLabel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JScrollPane SPblankTable;
    private JTable blankTable;
    private JButton searchButton;
    private JLabel infoLabel;
    private JButton deleteButton;
    private JTextField searchTextField;
    private JButton updateButton;
    private JLabel passwordLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;
    private JLabel phoneNUmberLabel;

    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public EditOfficeManager() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket

        // cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // save
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String managerName, managerPhone, managerAddress, managerEmail, managerUsername, managerPassword;

                managerName = nameTextField.getText();
                managerPhone = phoneNumberTextField.getText();
                managerAddress = addressTextField.getText();
                managerEmail = emailTextField.getText();
                managerUsername = usernameTextField.getText();
                managerPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Office_Manager(managerName, managerPhone, managerAddress, managerEmail, managerUsername, managerPassword)values(?,?,?,?,?,?)");
                    pst.setString(1, managerName);
                    pst.setString(2, managerPhone);
                    pst.setString(3, managerAddress);
                    pst.setString(4, managerEmail);
                    pst.setString(5, managerUsername);
                    pst.setString(6, managerPassword);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    nameTextField.setText("");
                    phoneNumberTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // search
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String managerID = searchTextField.getText();

                    pst = main.con.prepareStatement("select managerID, managerName, managerPhone, managerAddress, managerEmail, managerUsername, managerPassword from manager where managerID = ?");
                    pst.setString(1, managerID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String managerName = rs.getString(2);
                        String managerPhone = rs.getString(3);
                        String managerAddress = rs.getString(4);
                        String managerEmail = rs.getString(5);
                        String managerUsername = rs.getString(6);
                        String managerPassword = rs.getString(7);

                        nameTextField.setText(managerName);
                        phoneNumberTextField.setText(managerPhone);
                        addressTextField.setText(managerAddress);
                        emailTextField.setText(managerEmail);
                        usernameTextField.setText(managerUsername);
                        passwordTextField.setText(managerPassword);
                    } else {
                        nameTextField.setText("");
                        phoneNumberTextField.setText("");
                        addressTextField.setText("");
                        emailTextField.setText("");
                        usernameTextField.setText("");
                        passwordTextField.setText("");
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String managerID, managerName, managerPhone, managerAddress, managerEmail, managerUsername, managerPassword;

                managerID = searchTextField.getText();
                managerName = nameTextField.getText();
                managerPhone = phoneNumberTextField.getText();
                managerAddress = addressTextField.getText();
                managerEmail = emailTextField.getText();
                managerUsername = usernameTextField.getText();
                managerPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Office_Manager set managerName = ?, managerPhone = ?, managerAddress = ?, managerEmail = ?, managerUsername = ?, managerPassword = ? where managerID = ?");
                    pst.setString(1, managerName);
                    pst.setString(2, managerPhone);
                    pst.setString(3, managerAddress);
                    pst.setString(4, managerEmail);
                    pst.setString(5, managerUsername);
                    pst.setString(6, managerPassword);
                    pst.setString(7, managerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneNumberTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String managerID;
                managerID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Office_Manager where managerID = ?");
                    pst.setString(1,managerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneNumberTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Ticket");
            ResultSet rs = pst.executeQuery();
            blankTable.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

