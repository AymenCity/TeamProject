import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Refund extends JFrame {        // This refund class wil only be accessed by the "Admins".
    private JPanel mainPanel;
    private JTable refundTable;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField amountTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JPanel LowerPanel;
    private JComboBox typeComboBox;
    private JComboBox stateComboBox;
    private JComboBox saleIDComboBox;
    private JTextField currencyTextField;
    private JLabel flavourTextLabel;
    private JLabel TitleLabel;
    private JLabel SearchTextLabel;
    private JButton returnButton;
    private JButton confirmButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);

        main.connect();
        load_table();
        update_SaleComboBox();

        // SEARCH
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String paymentID = searchTextField.getText();

                    pst = main.con.prepareStatement("select paymentID, paymentAmount, paymentCurrency, paymentType, paymentState, saleID from Payment where paymentID = ?");
                    pst.setString(1, paymentID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String paymentAmount = rs.getString(2);
                        String paymentCurrency = rs.getString(3);
                        String paymentType = rs.getString(4);
                        String paymentState = rs.getString(5);
                        String saleID = rs.getString(6);

                        amountTextField.setText(paymentAmount);
                        currencyTextField.setText(paymentCurrency);
                        typeComboBox.setSelectedItem(paymentType);
                        stateComboBox.setSelectedItem(paymentState);
                        saleIDComboBox.setSelectedItem(saleID);
                    } else {
                        amountTextField.setText("");
                        currencyTextField.setText("");
                        typeComboBox.setSelectedIndex(0);
                        saleIDComboBox.setSelectedIndex(0);
                        stateComboBox.setSelectedIndex(0);
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
                String paymentID;
                paymentID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Payment where paymentID = ?");
                    pst.setString(1,paymentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    amountTextField.setText("");
                    currencyTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    stateComboBox.setSelectedIndex(0);
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
                String paymentAmount, paymentCurrency, paymentType, paymentState, saleID;

                paymentAmount = amountTextField.getText();
                paymentCurrency = currencyTextField.getText();
                paymentType = typeComboBox.getSelectedItem().toString();
                paymentState = stateComboBox.getSelectedItem().toString();
                saleID = saleIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("insert into Payment(paymentAmount, paymentCurrency, paymentType, paymentState, saleID)values(?,?,?,?,?)");
                    pst.setString(1, paymentAmount);
                    pst.setString(2, paymentCurrency);
                    pst.setString(3, paymentType);
                    pst.setString(4, paymentState);
                    pst.setString(5, saleID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    amountTextField.setText("");
                    currencyTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    stateComboBox.setSelectedIndex(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paymentID, paymentAmount, paymentCurrency, paymentType, paymentState, saleID;

                paymentID = searchTextField.getText();
                paymentAmount = amountTextField.getText();
                paymentCurrency = currencyTextField.getText();
                paymentType = typeComboBox.getSelectedItem().toString();
                paymentState = stateComboBox.getSelectedItem().toString();
                saleID = saleIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("update Payment(paymentAmount = ?, paymentCurrency = ?, paymentType = ?, paymentState = ?, saleID = ? where paymentID = ?");
                    pst.setString(1, paymentAmount);
                    pst.setString(2, paymentCurrency);
                    pst.setString(3, paymentType);
                    pst.setString(4, paymentState);
                    pst.setString(5, saleID);
                    pst.setString(6, paymentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    amountTextField.setText("");
                    currencyTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    stateComboBox.setSelectedIndex(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        // CANCEL
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });
    }

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Payment");
            ResultSet rs = pst.executeQuery();
            refundTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update_SaleComboBox() {
        try {
            pst = main.con.prepareStatement("select * from Air_Ticket_Sale");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                saleIDComboBox.addItem(rs.getString("saleID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

