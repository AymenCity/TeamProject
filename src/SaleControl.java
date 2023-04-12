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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class which allows the employees to alter the information of the Ticket Sale database
 * This allows the employees to create, update, search or delete a ticket sale
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
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
    private JComboBox typeComboBox;
    private JButton printButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    /**
     * A constructor which creates the GUI frame of Sales Control
     * Includes the main panel, labels, button functionalities
     */
    public SaleControl(){
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(1000,700);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Air_Ticket_Sale

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
                    String saleID = searchTextField.getText();

                    pst = main.con.prepareStatement("select saleID, saleType, saleTotal, saleCommission, saleGrandTotal, saleInterlineCurrencyRate, ticketID, agentID, customerID from Air_Ticket_Sale where saleID = ?");
                    pst.setString(1, saleID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String saleType = rs.getString(2);
                        String saleTotal = rs.getString(3);
                        String saleCommission = rs.getString(4);
                        String saleGrandTotal = rs.getString(5);
                        String saleInterlineCurrencyRate = rs.getString(6);
                        String ticketID = rs.getString(7);
                        String agentID = rs.getString(8);
                        String customerID = rs.getString(9);

                        typeComboBox.setSelectedItem(saleType);
                        saleTotalTextField.setText(saleTotal);
                        commisTextField.setText(saleCommission);
                        grandTotTextField.setText(saleGrandTotal);
                        currRateTextField.setText(saleInterlineCurrencyRate);
                        tickIDTextField.setText(ticketID);
                        agentIDTextField.setText(agentID);
                        custIDTextField.setText(customerID);

                    } else {
                        typeComboBox.setSelectedIndex(0);
                        saleTotalTextField.setText("");
                        commisTextField.setText("");
                        grandTotTextField.setText("");
                        currRateTextField.setText("");
                        tickIDTextField.setText("");
                        agentIDTextField.setText("");
                        custIDTextField.setText("");
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }
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
                String saleID;
                saleID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Air_Ticket_Sale where saleID = ?");
                    pst.setString(1,saleID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    typeComboBox.setSelectedIndex(0);
                    saleTotalTextField.setText("");
                    commisTextField.setText("");
                    grandTotTextField.setText("");
                    currRateTextField.setText("");
                    searchTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
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
                String saleType, saleTotal, saleCommission, saleGrandTotal, saleInterlineCurrencyRate, ticketID, agentID, customerID;

                saleType = typeComboBox.getSelectedItem().toString();
                saleTotal = saleTotalTextField.getText();
                saleCommission = commisTextField.getText();
                saleGrandTotal = grandTotTextField.getText();
                saleInterlineCurrencyRate = currRateTextField.getText();
                ticketID = tickIDTextField.getText();
                agentID = agentIDTextField.getText();
                customerID = custIDTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Air_Ticket_Sale(saleType, saleTotal, saleCommission, saleGrandTotal, saleInterlineCurrencyRate, ticketID, agentID, customerID)values(?,?,?,?,?,?,?,?)");
                    pst.setString(1, saleType);
                    pst.setString(2, saleTotal);
                    pst.setString(3, saleCommission);
                    pst.setString(4, saleGrandTotal);
                    pst.setString(5, saleInterlineCurrencyRate);
                    pst.setString(6, ticketID);
                    pst.setString(7, agentID);
                    pst.setString(8, customerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    typeComboBox.setSelectedIndex(0);
                    saleTotalTextField.setText("");
                    commisTextField.setText("");
                    grandTotTextField.setText("");
                    currRateTextField.setText("");
                    tickIDTextField.setText("");
                    agentIDTextField.setText("");
                    custIDTextField.setText("");
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
                String saleID, saleType, saleTotal, saleCommission, saleGrandTotal, saleInterlineCurrencyRate, ticketID, agentID, customerID;

                saleID = searchTextField.getText();
                saleType = typeComboBox.getSelectedItem().toString();
                saleTotal = saleTotalTextField.getText();
                saleCommission = commisTextField.getText();
                saleGrandTotal = grandTotTextField.getText();
                saleInterlineCurrencyRate = currRateTextField.getText();
                ticketID = tickIDTextField.getText();
                agentID = agentIDTextField.getText();
                customerID = custIDTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Air_Ticket_Sale set saleType = ?, saleTotal = ?, saleCommission = ?, saleGrandTotal = ?, saleInterlineCurrencyRate = ?, ticketID = ?, agentID = ?, customerID = ? where saleID = ?");
                    pst.setString(1, saleType);
                    pst.setString(2, saleTotal);
                    pst.setString(3, saleCommission);
                    pst.setString(4, saleGrandTotal);
                    pst.setString(5, saleInterlineCurrencyRate);
                    pst.setString(6, ticketID);
                    pst.setString(7, agentID);
                    pst.setString(8, customerID);
                    pst.setString(9, saleID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    saleTotalTextField.setText("");
                    commisTextField.setText("");
                    grandTotTextField.setText("");
                    currRateTextField.setText("");
                    tickIDTextField.setText("");
                    agentIDTextField.setText("");
                    custIDTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

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
         * An action listener which exports a database into a pdf file
         * Reference: https://www.youtube.com/watch?v=Zg7lS5sPN0M&ab_channel=jinujawadm
         */
        // PRINT
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(mainPanel, "Exporting to PDF is Successful");
                    String path = "database/print/sale.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Air_Ticket_Sale");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("saleID: " + rs.getString("saleID") + skip +
                                "saleType: " + rs.getString("saleType") + skip +
                                "saleTotal: " + rs.getString("saleTotal") + skip +
                                "saleCommission: " + rs.getString("saleCommission") + skip +
                                "saleGrandTotal: " + rs.getString("saleGrandTotal") + skip +
                                "saleInterlineCurrencyRate: " + rs.getString("saleInterlineCurrencyRate") + skip +
                                "ticketID: " + rs.getString("ticketID") + skip +
                                "agentID: " + rs.getString("agentID") + skip +
                                "customerID: " + rs.getString("customerID"));
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
            pst = main.con.prepareStatement("select * from Air_Ticket_Sale");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
