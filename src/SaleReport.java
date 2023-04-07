import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class SaleReport extends JFrame {
    private JPanel mainPanel;
    private JLabel TitleLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTable table1;
    private JLabel infoLabel;
    private JTextField timeGeneratedField;
    private JLabel timeGeneratedLabel;
    private JTextField agentIDTextField;
    private JLabel agentIDLabel;
    private JLabel saleIDLabel;
    private JTextField saleIDTextField;
    private JLabel paymentTypeLabel;
    private JTextField paymentTypeTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField airlineIDTextField;
    private JLabel airlineIDLabel;
    private JLabel saleTypeLabel;
    private JTextField saleTypeField;
    private JPanel salesReportMainPanel;
    private JTextField paymentCurrencyTextField;
    private JTextField ticketBlankTypeTextField;
    private JComboBox StypeComboBox;
    private JComboBox PtypeComboBox;
    private JComboBox TicComboBox;
    private JComboBox agentIDComboBox;
    private JComboBox airlineIDComboBox;
    private JComboBox saleIDComboBox;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public SaleReport() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket
        update_AgentComboBox();
        update_AirlineComboBox();
        update_SaleComboBox();
        saleComboBox_to_TextField();
        saleTypeField.setEditable(false);

        // SEARCH button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String reportID = searchTextField.getText();

                    pst = main.con.prepareStatement("select reportID, timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency, ticketBlankType from Air_Ticket_Sales_Report where reportID = ?");
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
                        String ticketBlankType = rs.getString(9);

                        timeGeneratedField.setText(timeGenerated);
                        agentIDTextField.setText(agentID);
                        airlineIDTextField.setText(airlineID);
                        saleIDTextField.setText(saleID);
                        saleTypeField.setText(saleType);
                        PtypeComboBox.setSelectedItem(paymentType);
                        paymentCurrencyTextField.setText(paymentCurrency);
                        TicComboBox.setSelectedItem(ticketBlankType);

                    } else {
                        timeGeneratedField.setText("");
                        agentIDTextField.setText("");
                        airlineIDTextField.setText("");
                        saleIDTextField.setText("");
                        saleTypeField.setText("");
                        PtypeComboBox.setSelectedIndex(0);
                        paymentCurrencyTextField.setText("");
                        TicComboBox.setSelectedIndex(0);
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
                    agentIDTextField.setText("");
                    airlineIDTextField.setText("");
                    saleIDTextField.setText("");
                    saleTypeField.setText("");
                    PtypeComboBox.setSelectedIndex(0);
                    paymentCurrencyTextField.setText("");
                    TicComboBox.setSelectedIndex(0);
                    searchTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });



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

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reportID, timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency, ticketBlankType;

                reportID = searchTextField.getText();
                timeGenerated = timeGeneratedField.getText();
                agentID = agentIDTextField.getText();
                airlineID = airlineIDTextField.getText();
                saleID = saleIDTextField.getText();
                saleType = saleTypeField.getText();
                paymentType = PtypeComboBox.getSelectedItem().toString();
                paymentCurrency = paymentCurrencyTextField.getText();
                ticketBlankType = TicComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("update Air_Ticket_Sales_Report set timeGenerated = ?, agentID = ?, airlineID = ?, saleID = ?, saleType = ?, paymentType = ?, paymentCurrency = ?, ticketBlankType = ? where reportID = ?");
                    pst.setString(1, timeGenerated);
                    pst.setString(2, agentID);
                    pst.setString(3, airlineID);
                    pst.setString(4, saleID);
                    pst.setString(5, saleType);
                    pst.setString(6, paymentType);
                    pst.setString(7, paymentCurrency);
                    pst.setString(8, ticketBlankType);
                    pst.setString(9, reportID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
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
                agentIDComboBox.addItem(rs.getString("agentID"));
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

    void saleComboBox_to_TextField() {
        String item = saleIDComboBox.getSelectedItem().toString();
        try {
            pst = main.con.prepareStatement("select * from Air_Ticket_Sale where saleID = ?");
            pst.setString(1,item);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                saleTypeField.setText(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}