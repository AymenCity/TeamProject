import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


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
    private JLabel blankIdLabel;
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

        // cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });

        // save button
        saveButton.addActionListener(new ActionListener() {
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

                /*LocalDateTime ticketFlightDate = LocalDateTime.parse(dateTextField.getText(),
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                LocalDateTime flightTime = LocalDateTime.parse(timeTextField.getText(),
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));*/


                try {
                    pst = main.con.prepareStatement("insert into Ticket(ticketID, ticketBlankType, ticketBlankNumber, ticketPrice, ticketStatus)values(?,?,?,?,?)");
                    pst.setString(1, ticketID);
                    pst.setString(2, ticketBlankType);
                    pst.setString(3, ticketBlankNumber);
                    pst.setString(4, ticketPrice);
                    // pst.setString(5, ticketFlightDate);
                    // pst.setString(6, ticketFlightTime);
                    pst.setString(5, ticketStatus);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    blankIdTextField.setText("");
                    typeTextField.setText("");
                    numberTextField.setText("");
                    priceTextField.setText("");
                    dateTextField.setText("");
                    timeTextField.setText("");
                    statusTextField.setText("");
                    blankIdTextField.requestFocus();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }


               /* try {

                    pst = main.con.prepareStatement("insert into Ticket(ticketID,ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus)values(?,?,?,?,?,?,?)");
                    pst.setInt(1, 1);
                    pst.setInt(2, Integer.parseInt(type));
                    pst.setInt(3, Integer.parseInt(blankID));
                    pst.setInt(4, Integer.parseInt(number));
                    pst.setDate(5, new java.sql.Date(flightDate.atZone(ZoneId.systemDefault())
                            .toInstant().toEpochMilli()));
                    pst.setDate(6, new java.sql.Date(flightTime.atZone(ZoneId.systemDefault())
                            .toInstant().toEpochMilli()));
                    pst.setString(7, status);
                    //pst.setInt(8, 11001);
                    //pst.setString(9, "Jeremy Clarky");

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    blankIdTextField.setText("");
                    typeTextField.setText("");
                    numberTextField.setText("");
                    priceTextField.setText("");
                    dateTextField.setText("");
                    timeTextField.setText("");
                    statusTextField.setText("");


                } catch (Exception exception) {
                    exception.printStackTrace();
                } */

            }
        });


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
                        String ticketStatus = rs.getString(5);

                        typeTextField.setText(ticketBlankType);
                        numberTextField.setText(ticketBlankNumber);
                        priceTextField.setText(ticketPrice);
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
