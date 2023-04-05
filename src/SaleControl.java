import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SaleControl extends JFrame {
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JLabel TitleLabel;
    private JButton deleteButton;
    private JTable table1;
    private JLabel FlavourTextLabel;
    private JTextField saleIDTextField;
    private JLabel saleTypeLabel;
    private JTextField saleTotalTextField;
    private JLabel saleTotalButton;
    private JTextField grandTotTextField;
    private JLabel grandTotButton;
    private JLabel tickIDButton;
    private JTextField tickIDTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField commisTextField;
    private JTextField currRateTextField;
    private JLabel commisLabel;
    private JLabel currRateLabel;
    private JTextField agentIDTextField;
    private JTextField custIDTextField;
    private JTextField saleTypeTextField;
    private JLabel AgentIDLabel;
    private JLabel custIDLabel;
    private JScrollPane saleControlTable;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public SaleControl(){
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,600);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Air_Ticket_Sale

        // SEARCH button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String saleID = searchTextField.getText();

                    pst = main.con.prepareStatement("select saleID, saleType, saleTotal, saleCommission, saleGrandTotal, saleInterlineCurrencyRate from Air_Ticket_Sale where saleID = ?");
                    pst.setString(1, saleID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String saleType = rs.getString(2);
                        String saleTotal = rs.getString(3);
                        String saleCommission = rs.getString(4);
                        String saleGrandTotal = rs.getString(5);
                        String saleInterlineCurrencyRate = rs.getString(6);

                        saleTypeTextField.setText(saleType);
                        saleTotalTextField.setText(saleTotal);
                        commisTextField.setText(saleCommission);
                        grandTotTextField.setText(saleGrandTotal);
                        currRateTextField.setText(saleInterlineCurrencyRate);

                    } else {
                        saleTypeTextField.setText("");
                        saleTotalTextField.setText("");
                        commisTextField.setText("");
                        grandTotTextField.setText("");
                        currRateTextField.setText("");
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }


            }
        });

        // DELETE button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String saleID;
                saleID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Air_Ticket_Sale where saleID = ?");
                    pst.setString(1,saleID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    saleIDTextField.setText("");
                    saleTypeTextField.setText("");
                    saleTotalTextField.setText("");
                    commisTextField.setText("");
                    grandTotTextField.setText("");
                    currRateTextField.setText("");
                    saleIDTextField.requestFocus();
                    searchTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // SAVE button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String saleID, saleType, saleTotal, saleCommission, saleGrandTotal, saleInterlineCurrencyRate;

                saleID = saleIDTextField.getText();
                saleType = saleTypeTextField.getText();
                saleTotal = saleTotalTextField.getText();
                saleCommission = commisTextField.getText();
                saleGrandTotal = grandTotTextField.getText();
                saleInterlineCurrencyRate = currRateTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Air_Ticket_Sale(saleType, saleTotal, saleCommission, saleGrandTotal, saleInterlineCurrencyRate)values(?,?,?,?,?)");
                    pst.setString(1, saleType);
                    pst.setString(2, saleTotal);
                    pst.setString(3, saleCommission);
                    pst.setString(4, saleGrandTotal);
                    pst.setString(5, saleInterlineCurrencyRate);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    saleIDTextField.setText("");
                    saleTypeTextField.setText("");
                    saleTotalTextField.setText("");
                    commisTextField.setText("");
                    grandTotTextField.setText("");
                    currRateTextField.setText("");
                    saleIDTextField.requestFocus();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // UPDATE button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String saleID, saleType, saleTotal, saleCommission, saleGrandTotal, saleInterlineCurrencyRate;

                saleID = saleIDTextField.getText();
                saleType = saleTypeTextField.getText();
                saleTotal = saleTotalTextField.getText();
                saleCommission = commisTextField.getText();
                saleGrandTotal = grandTotTextField.getText();
                saleInterlineCurrencyRate = currRateTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Air_Ticket_Sale set saleType = ?, saleTotal = ?, saleCommission = ?, saleGrandTotal = ?, saleInterlineCurrencyRate = ? where saleID = ?");
                    pst.setString(1, saleType);
                    pst.setString(2, saleTotal);
                    pst.setString(3, saleCommission);
                    pst.setString(4, saleGrandTotal);
                    pst.setString(5, saleInterlineCurrencyRate);
                    pst.setString(6, saleID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    saleIDTextField.setText("");
                    saleTypeTextField.setText("");
                    saleTotalTextField.setText("");
                    commisTextField.setText("");
                    grandTotTextField.setText("");
                    currRateTextField.setText("");
                    saleIDTextField.requestFocus();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // CANCEL button
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
            pst = main.con.prepareStatement("select * from Air_Ticket_Sale");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
