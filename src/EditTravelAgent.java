import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditTravelAgent extends JFrame {
    private JPanel mainPanel;
    private JLabel stockControlLabel;
    private JLabel nameLabel;
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
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JPanel parentPanel;
    private JLabel emailLabel;
    private JLabel addressLabel;
    private JTextField phoneTextField;
    private JLabel phoneNumberLabel;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public EditTravelAgent() {
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
                String agentName, agentPhone, agentAddress, agentEmail, agentUsername, agentPassword;

                agentName = nameTextField.getText();
                agentPhone = phoneTextField.getText();
                agentAddress = addressTextField.getText();
                agentEmail = emailTextField.getText();
                agentUsername = usernameTextField.getText();
                agentPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Travel_Agent(agentName, agentPhone, agentAddress, agentEmail, agentUsername, agentPassword)values(?,?,?,?,?,?)");
                    pst.setString(1, agentName);
                    pst.setString(2, agentPhone);
                    pst.setString(3, agentAddress);
                    pst.setString(4, agentEmail);
                    pst.setString(5, agentUsername);
                    pst.setString(6, agentPassword);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    nameTextField.setText("");
                    phoneTextField.setText("");
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
                    String agentID = searchTextField.getText();

                    pst = main.con.prepareStatement("select agentID, agentName, agentPhone, agentAddress, agentEmail, agentUsername, agentPassword from Travel_Agent where agentID = ?");
                    pst.setString(1, agentID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String agentName = rs.getString(2);
                        String agentPhone = rs.getString(3);
                        String agentAddress = rs.getString(4);
                        String agentEmail = rs.getString(5);
                        String agentUsername = rs.getString(6);
                        String agentPassword = rs.getString(7);

                        nameTextField.setText(agentName);
                        phoneTextField.setText(agentPhone);
                        addressTextField.setText(agentAddress);
                        emailTextField.setText(agentEmail);
                        usernameTextField.setText(agentUsername);
                        passwordTextField.setText(agentPassword);
                    } else {
                        nameTextField.setText("");
                        phoneTextField.setText("");
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
                String agentID, agentName, agentPhone, agentAddress, agentEmail, agentUsername, agentPassword;

                agentID = searchTextField.getText();
                agentName = nameTextField.getText();
                agentPhone = phoneTextField.getText();
                agentAddress = addressTextField.getText();
                agentEmail = emailTextField.getText();
                agentUsername = usernameTextField.getText();
                agentPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Travel_Agent set agentName = ?, agentPhone = ?, agentAddress = ?, agentEmail = ?, agentUsername = ?, agentPassword = ? where agentID = ?");
                    pst.setString(1, agentName);
                    pst.setString(2, agentPhone);
                    pst.setString(3, agentAddress);
                    pst.setString(4, agentEmail);
                    pst.setString(5, agentUsername);
                    pst.setString(6, agentPassword);
                    pst.setString(7, agentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneTextField.setText("");
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
                String agentID;
                agentID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Travel_Agent where agentID = ?");
                    pst.setString(1,agentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneTextField.setText("");
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
            pst = main.con.prepareStatement("select * from Travel_Agent");
            ResultSet rs = pst.executeQuery();
            blankTable.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
