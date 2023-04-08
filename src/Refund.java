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
    private JPanel FlavourTextLabel;
    private JComboBox typeComboBox;
    private JComboBox stateComboBox;
    private JComboBox saleIDComboBox;
    private JTextField currencyTextField;
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
                String timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency, ticketBlankType;

                timeGenerated = timeGeneratedField.getText();
                agentID = agentIDTextField.getText();
                airlineID = airlineIDTextField.getText();
                saleID = saleIDTextField.getText();
                saleType = saleTypeField.getText();
                paymentType = PtypeComboBox.getSelectedItem().toString();
                paymentCurrency = paymentCurrencyTextField.getText();
                ticketBlankType = TicComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("insert into Air_Ticket_Sales_Report(timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency, ticketBlankType)values(?,?,?,?,?,?,?,?)");
                    pst.setString(1, timeGenerated);
                    pst.setString(2, agentID);
                    pst.setString(3, airlineID);
                    pst.setString(4, saleID);
                    pst.setString(5, saleType);
                    pst.setString(6, paymentType);
                    pst.setString(7, paymentCurrency);
                    pst.setString(8, ticketBlankType);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    timeGeneratedField.setText("");
                    agentIDTextField.setText("");
                    airlineIDTextField.setText("");
                    saleIDTextField.setText("");
                    saleTypeField.setText("");
                    PtypeComboBox.setSelectedIndex(0);
                    paymentCurrencyTextField.setText("");
                    TicComboBox.setSelectedIndex(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Update Confirmed!");
            }
        });
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

