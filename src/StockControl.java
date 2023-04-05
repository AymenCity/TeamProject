import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class StockControl extends JFrame {
    private JPanel mainPanel;
    private JLabel stockControlLabel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField blankIdTextField;
    private JTextField numberTextField;
    private JTextField priceTextField;
    private JTextField dateTextField;
    private JTextField timeTextField;
    private JTextField statusTextField;
    private JLabel typeLabel;
    private JLabel numberLabel;
    private JLabel priceLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JLabel statusLabel;
    private JTable blankTable;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField typeTextField;
    private JScrollPane SPblankTable;
    private JTextField searchTextField;
    private JLabel infoLabel;
    private JButton generateStockTurnoverReportButton;
    private JComboBox typeComboBox;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public StockControl() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket

        // CANCEL button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });

        // GENERATE REPORT button
        generateStockTurnoverReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SaleReport saleReport = new SaleReport();
            }
        });

        // SAVE button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus;

                ticketBlankType = typeComboBox.getSelectedItem().toString();
                ticketBlankNumber = numberTextField.getText();
                ticketPrice = priceTextField.getText();
                ticketFlightDate = dateTextField.getText();
                ticketFlightTime = timeTextField.getText();
                Timestamp ts = Timestamp.valueOf(ticketFlightTime);
                ticketStatus = statusTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Ticket(ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus)values(?,?,?,?,?,?)");
                    //pst.setString(1, ticketID);
                    //String typeValue = typeComboBox.getSelectedItem().toString();
                    pst.setString(1, ticketBlankType);
                    pst.setString(2, ticketBlankNumber);
                    pst.setString(3, ticketPrice);
                    pst.setString(4, ticketFlightDate);
                    pst.setString(5, ticketFlightTime);
                    pst.setString(6, ticketStatus);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    numberTextField.setText("");
                    priceTextField.setText("");
                    dateTextField.setText("");
                    timeTextField.setText("");
                    statusTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // SEARCH button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ticketID = searchTextField.getText();

                    pst = main.con.prepareStatement("select ticketID, ticketBlankType, ticketBlankNumber, ticketPrice, ticketStatus from Ticket where ticketID = ?");
                    pst.setString(1, ticketID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String ticketBlankType = rs.getString(2);
                        String ticketBlankNumber = rs.getString(3);
                        String ticketPrice = rs.getString(4);
                        //String ticketFlightDate = rs.getString(5);
                        //S ticketFlightTime = rs.getTimestamp(6);
                        String ticketStatus = rs.getString(5);

                        typeTextField.setText(ticketBlankType);
                        numberTextField.setText(ticketBlankNumber);
                        priceTextField.setText(ticketPrice);
                        //dateTextField = setText(ticketFlightDate);
                        //timeTextField = setType(ticketFlightTime);
                        //Timestamp ts = Timestamp.valueOf(ticketFlightTime);
                        statusTextField.setText(ticketStatus);

                    } else {
                        typeTextField.setText("");
                        numberTextField.setText("");
                        priceTextField.setText("");
                        statusTextField.setText("");
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // UPDATE button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketID, ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus;

                ticketID = blankIdTextField.getText();
                ticketBlankType = typeTextField.getText();
                ticketBlankNumber = numberTextField.getText();
                ticketPrice = priceTextField.getText();
                ticketFlightDate = dateTextField.getText();
                ticketFlightTime = timeTextField.getText();
                ticketStatus = statusTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Ticket set ticketBlankType = ?, ticketBlankNumber = ?, ticketPrice = ?, ticketStatus = ? where ticketID = ?");
                    pst.setString(1, ticketBlankType);
                    pst.setString(2, ticketBlankNumber);
                    pst.setString(3, ticketPrice);
                    // pst.setString(5, ticketFlightDate);
                    // pst.setString(6, ticketFlightTime);
                    pst.setString(4, ticketStatus);
                    pst.setString(5, ticketID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    blankIdTextField.setText("");
                    typeTextField.setText("");
                    numberTextField.setText("");
                    priceTextField.setText("");
                    //dateTextField.setText("");
                    //timeTextField.setText("");
                    statusTextField.setText("");
                    blankIdTextField.requestFocus();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // DELETE button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketID;
                ticketID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Ticket where ticketID = ?");
                    pst.setString(1,ticketID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    blankIdTextField.setText("");
                    typeTextField.setText("");
                    numberTextField.setText("");
                    priceTextField.setText("");
                    //dateTextField.setText("");
                    //timeTextField.setText("");
                    statusTextField.setText("");
                    blankIdTextField.requestFocus();
                    searchTextField.setText("");
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
