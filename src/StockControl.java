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
                String ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus;

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
                    pst = main.con.prepareStatement("insert into Ticket(ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus)values(?,?,?,?,?,?)");
                    pst.setString(1, ticketBlankType);
                    pst.setString(2, ticketBlankNumber);
                    pst.setString(3, ticketPrice);
                    pst.setString(4, ticketFlightDate);
                    pst.setString(5, ticketFlightTime);
                    pst.setString(6, ticketStatus);
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
