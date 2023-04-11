import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class R3_SaleReport extends JFrame {
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTable table1;
    private JLabel infoLabel;
    private JTextField timeGeneratedField;
    private JLabel timeGeneratedLabel;
    private JLabel saleIDLabel;
    private JLabel paymentTypeLabel;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JLabel airlineIDLabel;
    private JTextField paymentCurrencyTextField;
    private JComboBox PtypeComboBox;
    private JComboBox airlineIDComboBox;
    private JComboBox saleIDComboBox;
    private JLabel TitleLabel;
    private JButton printButton;
    private JLabel infoLabel2;
    private JLabel infoLabel3;
    private JButton searchButton2;
    private JComboBox searchAgentBox;
    private JComboBox searchSaleBox;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public R3_SaleReport() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(1000, 1000);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        update_AgentComboBox();
        update_AirlineComboBox();
        update_SaleComboBox();
        //saleComboBox_to_TextField();

        // SEARCH button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String reportID = searchTextField.getText();

                    pst = main.con.prepareStatement("select reportID, timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency from Air_Ticket_Sales_Report where reportID = ?");
                    pst.setString(1, reportID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String timeGenerated = rs.getString(2);
                        String agentID = rs.getString(3);
                        String airlineID = rs.getString(4);
                        String saleID = rs.getString(5);
                        String saleType = rs.getString(6);
                        String paymentType = rs.getString(7);
                        String paymentCurrency = rs.getString(8);

                        timeGeneratedField.setText(timeGenerated);
                        searchAgentBox.setSelectedItem(agentID);
                        airlineIDComboBox.setSelectedItem(airlineID);
                        saleIDComboBox.setSelectedItem(saleID);
                        searchSaleBox.setSelectedItem(saleType);
                        PtypeComboBox.setSelectedItem(paymentType);
                        paymentCurrencyTextField.setText(paymentCurrency);

                    } else {
                        timeGeneratedField.setText("");
                        searchAgentBox.setSelectedIndex(0);
                        airlineIDComboBox.setSelectedItem(0);
                        saleIDComboBox.setSelectedItem(0);
                        searchSaleBox.setSelectedIndex(0);
                        PtypeComboBox.setSelectedIndex(0);
                        paymentCurrencyTextField.setText("");
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
                String reportID;
                reportID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Air_Ticket_Sales_Report where reportID = ?");
                    pst.setString(1,reportID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    timeGeneratedField.setText("");
                    searchAgentBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    searchSaleBox.setSelectedIndex(0);
                    PtypeComboBox.setSelectedIndex(0);
                    paymentCurrencyTextField.setText("");
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
                String timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency, ticketBlankType;

                timeGenerated = timeGeneratedField.getText();
                agentID = searchAgentBox.getSelectedItem().toString();
                airlineID = airlineIDComboBox.getSelectedItem().toString();
                saleID = saleIDComboBox.getSelectedItem().toString();
                saleType = searchSaleBox.getSelectedItem().toString();
                paymentType = PtypeComboBox.getSelectedItem().toString();
                paymentCurrency = paymentCurrencyTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Air_Ticket_Sales_Report(timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency)values(?,?,?,?,?,?,?)");
                    pst.setString(1, timeGenerated);
                    pst.setString(2, agentID);
                    pst.setString(3, airlineID);
                    pst.setString(4, saleID);
                    pst.setString(5, saleType);
                    pst.setString(6, paymentType);
                    pst.setString(7, paymentCurrency);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    timeGeneratedField.setText("");
                    searchAgentBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    searchSaleBox.setSelectedIndex(0);
                    PtypeComboBox.setSelectedIndex(0);
                    paymentCurrencyTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reportID, timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency, ticketBlankType;

                reportID = searchTextField.getText();
                timeGenerated = timeGeneratedField.getText();
                agentID = searchAgentBox.getSelectedItem().toString();
                airlineID = airlineIDComboBox.getSelectedItem().toString();
                saleID = saleIDComboBox.getSelectedItem().toString();
                saleType = searchSaleBox.getSelectedItem().toString();
                paymentType = PtypeComboBox.getSelectedItem().toString();
                paymentCurrency = paymentCurrencyTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Air_Ticket_Sales_Report set timeGenerated = ?, agentID = ?, airlineID = ?, saleID = ?, saleType = ?, paymentType = ?, paymentCurrency = ? where reportID = ?");
                    pst.setString(1, timeGenerated);
                    pst.setString(2, agentID);
                    pst.setString(3, airlineID);
                    pst.setString(4, saleID);
                    pst.setString(5, saleType);
                    pst.setString(6, paymentType);
                    pst.setString(7, paymentCurrency);
                    pst.setString(8, reportID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    timeGeneratedField.setText("");
                    searchAgentBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    searchSaleBox.setSelectedIndex(0);
                    PtypeComboBox.setSelectedIndex(0);
                    paymentCurrencyTextField.setText("");
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


        // LOAD TABLE BY SEARCH
        searchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String agentID, saleType;

                agentID = searchAgentBox.getSelectedItem().toString();
                saleType = searchSaleBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("select * from Air_Ticket_Sale where agentID = ? and saleType = ?");
                    pst.setString(1, agentID);
                    pst.setString(2, saleType);
                    ResultSet rs = pst.executeQuery();
                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                    JOptionPane.showMessageDialog(mainPanel, "Table Loaded");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Air_Ticket_Sales_Report");
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
                searchAgentBox.addItem(rs.getString("agentID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update_AirlineComboBox() {
        try {
            pst = main.con.prepareStatement("select * from Airline");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                airlineIDComboBox.addItem(rs.getString("airlineID"));
            }
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