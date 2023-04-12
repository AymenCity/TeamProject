import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

/**
 * A class which allows the employees to alter the information of the Ticket database
 * This allows the employees to create, update, search or delete a ticket
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
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
    private JComboBox statusComboBox;
    private JLabel airlineIDLabel;
    private JLabel agentIDLabel;
    private JComboBox airlineIDComboBox;
    private JComboBox agentIDComboBox;
    private JButton printButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    /**
     * A constructor which creates the GUI frame of Stock Control
     * Includes the main panel, labels, button functionalities
     */
    public StockControl() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(1000, 700);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket
        update_AirlineComboBox();
        update_AgentComboBox();

        dateTextField.setText("YYYY-MM-DD");
        timeTextField.setText("YYYY-MM-DD HH:MM:SS");

        /**
         * An action listener which takes the user back to the Welcome page
         */
        // CANCEL button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        /**
         * An action listener which adds new data into the database
         * This obtains any information from the text fields
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
        // SAVE button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus, airlineID, agentID;

                ticketBlankType = typeComboBox.getSelectedItem().toString();
                ticketBlankNumber = numberTextField.getText();
                ticketPrice = priceTextField.getText();
                ticketFlightDate = dateTextField.getText();
                ticketFlightTime = timeTextField.getText();
                ticketStatus = statusComboBox.getSelectedItem().toString();
                airlineID = airlineIDComboBox.getSelectedItem().toString();
                agentID = agentIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("insert into Ticket(ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus, airlineID, agentID)values(?,?,?,?,?,?,?,?)");
                    pst.setString(1, ticketBlankType);
                    pst.setString(2, ticketBlankNumber);
                    pst.setString(3, ticketPrice);
                    pst.setString(4, ticketFlightDate);
                    pst.setString(5, ticketFlightTime);
                    pst.setString(6, ticketStatus);
                    pst.setString(7, airlineID);
                    pst.setString(8, agentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    typeComboBox.setSelectedIndex(0);
                    numberTextField.setText("");
                    priceTextField.setText("");
                    dateTextField.setText("YYYY-MM-DD");
                    timeTextField.setText("YYYY-MM-DD HH:MM:SS");
                    statusComboBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    agentIDComboBox.setSelectedIndex(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        /**
         * An action listener which searches any data from the database from the search text field
         * This results in the data being filled out automatically in the text fields
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
        // SEARCH button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ticketID = searchTextField.getText();

                    pst = main.con.prepareStatement("select ticketID, ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus, airlineID, agentID from Ticket where ticketID = ?");
                    pst.setString(1, ticketID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String ticketBlankType = rs.getString(2);
                        String ticketBlankNumber = rs.getString(3);
                        String ticketPrice = rs.getString(4);
                        String ticketFlightDate = rs.getString(5);
                        String ticketFlightTime = rs.getString(6);
                        String ticketStatus = rs.getString(7);
                        String airlineID = rs.getString(8);
                        String agentID = rs.getString(9);

                        typeComboBox.setSelectedItem(ticketBlankType);
                        numberTextField.setText(ticketBlankNumber);
                        priceTextField.setText(ticketPrice);
                        dateTextField.setText(ticketFlightDate);
                        timeTextField.setText(ticketFlightTime);
                        statusComboBox.setSelectedItem(ticketStatus);
                        airlineIDComboBox.setSelectedItem(airlineID);
                        agentIDComboBox.setSelectedItem(agentID);

                    } else {
                        typeComboBox.setSelectedIndex(0);
                        numberTextField.setText("");
                        priceTextField.setText("");
                        dateTextField.setText("YYYY-MM-DD");
                        timeTextField.setText("YYYY-MM-DD HH:MM:SS");
                        statusComboBox.setSelectedIndex(0);
                        airlineIDComboBox.setSelectedIndex(0);
                        agentIDComboBox.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        /**
         * An action listener which updates the data from the database
         * This results in the data being edited and changed
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
        // UPDATE button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketID, ticketBlankType, ticketBlankNumber, ticketPrice, ticketFlightDate, ticketFlightTime, ticketStatus, airlineID, agentID;

                ticketID = searchTextField.getText();
                ticketBlankType = typeComboBox.getSelectedItem().toString();
                ticketBlankNumber = numberTextField.getText();
                ticketPrice = priceTextField.getText();
                ticketFlightDate = dateTextField.getText();
                ticketFlightTime = timeTextField.getText();
                ticketStatus = statusComboBox.getSelectedItem().toString();
                airlineID = airlineIDComboBox.getSelectedItem().toString();
                agentID = agentIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("update Ticket set ticketBlankType = ?, ticketBlankNumber = ?, ticketPrice = ?, ticketFlightDate = ?, ticketFlightTime = ?, ticketStatus = ?, airlineID = ?, agentID = ? where ticketID = ?");
                    pst.setString(1, ticketBlankType);
                    pst.setString(2, ticketBlankNumber);
                    pst.setString(3, ticketPrice);
                    pst.setString(4, ticketFlightDate);
                    pst.setString(5, ticketFlightTime);
                    pst.setString(6, ticketStatus);
                    pst.setString(7, airlineID);
                    pst.setString(8, agentID);
                    pst.setString(9, ticketID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    numberTextField.setText("");
                    priceTextField.setText("");
                    dateTextField.setText("YYYY-MM-DD");
                    timeTextField.setText("YYYY-MM-DD HH:MM:SS");
                    statusComboBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    agentIDComboBox.setSelectedIndex(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        /**
         * An action listener which removes a data from the database based on the search text field
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
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
                    typeComboBox.setSelectedIndex(0);
                    numberTextField.setText("");
                    priceTextField.setText("");
                    dateTextField.setText("YYYY-MM-DD");
                    timeTextField.setText("YYYY-MM-DD HH:MM:SS");
                    statusComboBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    agentIDComboBox.setSelectedIndex(0);
                    searchTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        /**
         * An action listener which exports a database into a pdf file
         * Reference: https://www.youtube.com/watch?v=Zg7lS5sPN0M&ab_channel=jinujawadm
         */
        // PRINT
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(mainPanel, "Exporting to PDF is Successful");
                    String path = "database/print/ticket.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Ticket");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("ticketID: " + rs.getString("ticketID") + skip +
                                "ticketBlankType: " + rs.getString("ticketBlankType") + skip +
                                "ticketBlankNumber: " + rs.getString("ticketBlankNumber") + skip +
                                "ticketPrice: " + rs.getString("ticketPrice") + skip +
                                "ticketFlightDate: " + rs.getString("ticketFlightDate") + skip +
                                "ticketFlightTime: " + rs.getString("ticketFlightTime") + skip +
                                "ticketStatus: " + rs.getString("ticketStatus") + skip +
                                "airlineID: " + rs.getString("airlineID") + skip +
                                "agentID: " + rs.getString("agentID"));
                        document.add(paragraph);
                        document.add(new Paragraph(" " + skip));
                    }
                    document.close();

                } catch (DocumentException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    /**
     * A method which loads the data from the database
     * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
     */
    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Ticket");
            ResultSet rs = pst.executeQuery();
            blankTable.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method which automatically updates the combo box based on the total of 'airlineID' on the database
     * Recommended when using foreign keys
     */
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

    /**
     * A method which automatically updates the combo box based on the total of 'agentID' on the database
     * Recommended when using foreign keys
     */
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


}
