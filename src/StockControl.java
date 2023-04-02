import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.time.LocalDate;



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
    Connection con;
    PreparedStatement pst;

    public StockControl() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);

        // calling database connection from Main
        Main main = new Main();
        main.connect();

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
                String blankID, type, number, price, flightDate, flightTime, status;

                blankID = blankIdTextField.getText();
                type = typeTextField.getText();
                number = numberTextField.getText();
                price = priceTextField.getText();
                flightDate = dateTextField.getText();
                flightTime = timeTextField.getText();
                status = statusTextField.getText();

                try {

                    pst = main.con.prepareStatement("insert into Ticket(ticketID,ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus, airlineID, managerName)values('1','420','111111', '420', LocalDate.parse(\"2018-05-05\"), LocalDate.parse(\"2018-05-05\"), 'VALID', '1101', 'Jeremy Clarkson')");
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

            }
        });
    }


}
