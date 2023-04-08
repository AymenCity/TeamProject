import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Alerts extends JFrame {


    private JLabel alertsLabel;
    private JTable table1;
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField alertIDTextField;
    private JLabel alertSevLabel;
    private JLabel alertIDLabel;
    private JLabel flavourTextLabel;
    private JTextField alertDescTextField;
    private JLabel alertDescLabel;
    private JLabel alertStatusLabel;
    private JLabel dateCreatedLabel;
    private JTextField dateCreatedTextField;
    private JLabel lastModLabel;
    private JTextField lastModTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton printButton;
    private JButton cancelButton;
    private JComboBox sevComboBox;
    private JComboBox staComboBox;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public Alerts() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,600);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        main.connect();
        load_table();

        // CANCEL
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // SEARCH
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String alertID = searchTextField.getText();

                    pst = main.con.prepareStatement("select alertID, alertSeverity, alertDescription, alertStatus, dateCreated, lastModified from Alerts where alertID = ?");
                    pst.setString(1, alertID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String alertSeverity = rs.getString(2);
                        String alertDescription = rs.getString(3);
                        String alertStatus = rs.getString(4);
                        String dateCreated = rs.getString(5);
                        String lastModified = rs.getString(6);

                        sevComboBox.setSelectedItem(alertSeverity);
                        alertDescTextField.setText(alertDescription);
                        staComboBox.setSelectedItem(alertStatus);
                        dateCreatedTextField.setText(dateCreated);
                        lastModTextField.setText(lastModified);
                    } else {
                        sevComboBox.setSelectedIndex(0);
                        alertDescTextField.setText("");
                        staComboBox.setSelectedItem(0);
                        dateCreatedTextField.setText("YYYY-MM-DD");
                        lastModTextField.setText("YYYY-MM-DD");
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // DELETE
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alertID;
                alertID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Alerts where alertID = ?");
                    pst.setString(1,alertID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    sevComboBox.setSelectedIndex(0);
                    alertDescTextField.setText("");
                    staComboBox.setSelectedItem(0);
                    dateCreatedTextField.setText("YYYY-MM-DD");
                    lastModTextField.setText("YYYY-MM-DD");
                    searchTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // SAVE
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alertSeverity, alertDescription, alertStatus, dateCreated, lastModified;

                alertSeverity = sevComboBox.getSelectedItem().toString();
                alertDescription = alertDescTextField.getText();
                alertStatus = staComboBox.getSelectedItem().toString();
                dateCreated = dateCreatedTextField.getText();
                lastModified = lastModTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Alerts(alertSeverity, alertDescription, alertStatus, dateCreated, lastModified)values(?,?,?,?,?)");
                    pst.setString(1, alertSeverity);
                    pst.setString(2, alertDescription);
                    pst.setString(3, alertStatus);
                    pst.setString(4, dateCreated);
                    pst.setString(5, lastModified);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    sevComboBox.setSelectedIndex(0);
                    alertDescTextField.setText("");
                    staComboBox.setSelectedIndex(0);
                    dateCreatedTextField.setText("YYYY-MM-DD");
                    lastModTextField.setText("YYYY-MM-DD");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alertID, alertSeverity, alertDescription, alertStatus, dateCreated, lastModified;

                alertID = searchTextField.getText();
                alertSeverity = sevComboBox.getSelectedItem().toString();
                alertDescription = alertDescTextField.getText();
                alertStatus = staComboBox.getSelectedItem().toString();
                dateCreated = dateCreatedTextField.getText();
                lastModified = lastModTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Alerts set alertSeverity = ?, alertDescription = ?, alertStatus = ?, dateCreated = ?, lastModified = ? where alertID = ?");
                    pst.setString(1, alertSeverity);
                    pst.setString(2, alertDescription);
                    pst.setString(3, alertStatus);
                    pst.setString(4, dateCreated);
                    pst.setString(5, lastModified);
                    pst.setString(6, alertID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    sevComboBox.setSelectedIndex(0);
                    alertDescTextField.setText("");
                    staComboBox.setSelectedIndex(0);
                    dateCreatedTextField.setText("YYYY-MM-DD");
                    lastModTextField.setText("YYYY-MM-DD");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Alerts");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
