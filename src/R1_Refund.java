import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class R1_Refund extends JFrame {        // This refund class wil only be accessed by the "Admins".
    private JPanel mainPanel;
    private JTable refundTable;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField dateTextField;
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
    private JButton printButton;
    private JTextField reasonTextField;
    private JButton returnButton;
    private JButton confirmButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public R1_Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(1000, 700);   // window resolution
        setVisible(true);

        main.connect();
        load_table();
        update_SaleComboBox();

        // SEARCH
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String refundID = searchTextField.getText();

                    pst = main.con.prepareStatement("select refundID, refundReason, paymentDate, paymentCurrency, paymentType, saleID from Refund where refundID = ?");
                    pst.setString(1, refundID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String refundReason = rs.getString(2);
                        String paymentDate = rs.getString(3);
                        String paymentCurrency = rs.getString(4);
                        String paymentType = rs.getString(5);
                        String saleID = rs.getString(6);

                        reasonTextField.setText(refundReason);
                        dateTextField.setText(paymentDate);
                        currencyTextField.setText(paymentCurrency);
                        typeComboBox.setSelectedItem(paymentType);
                        saleIDComboBox.setSelectedItem(saleID);
                    } else {
                        reasonTextField.setText("");
                        dateTextField.setText("");
                        typeComboBox.setSelectedIndex(0);
                        saleIDComboBox.setSelectedIndex(0);
                        currencyTextField.setText("");
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
                String refundID;
                refundID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Refund where refundID = ?");
                    pst.setString(1, refundID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Refund Accepted!");
                    load_table();
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
                String refundReason, paymentDate, paymentCurrency, paymentType, saleID;

                refundReason = reasonTextField.getText();
                paymentDate = dateTextField.getText();
                paymentCurrency = currencyTextField.getText();
                paymentType = typeComboBox.getSelectedItem().toString();
                saleID = saleIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("insert into Refund(refundReason, paymentDate, paymentCurrency, paymentType, saleID)values(?,?,?,?,?)");
                    pst.setString(1, refundReason);
                    pst.setString(2, paymentDate);
                    pst.setString(3, paymentCurrency);
                    pst.setString(4, paymentType);
                    pst.setString(5, saleID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    reasonTextField.setText("");
                    dateTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    currencyTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String refundID, refundReason, paymentDate, paymentCurrency, paymentType, saleID;

                refundID = searchTextField.getText();
                refundReason = reasonTextField.getText();
                paymentDate = dateTextField.getText();
                paymentCurrency = currencyTextField.getText();
                paymentType = typeComboBox.getSelectedItem().toString();
                saleID = saleIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("update Payment set refundReason = ?, paymentDate = ?, paymentCurrency = ?, paymentType = ?, saleID = ? where refundID = ?");
                    pst.setString(1, refundReason);
                    pst.setString(2, paymentDate);
                    pst.setString(3, paymentCurrency);
                    pst.setString(4, paymentType);
                    pst.setString(5, saleID);
                    pst.setString(6, refundID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    reasonTextField.setText("");
                    dateTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    currencyTextField.setText("");
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
            }
        });
    }

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Refund");
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

