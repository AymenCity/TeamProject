import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class which allows the employees to alter the information of the Refund database
 * This allows the employees to create, update, search or delete a Refund
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
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

    /**
     * A constructor which creates the GUI frame of Refund
     * Includes the main panel, labels, button functionalities
     */
    public R1_Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(1000, 700);   // window resolution
        setVisible(true);

        main.connect();
        load_table();
        update_SaleComboBox();

        /**
         * An action listener which searches any data from the database from the search text field
         * This results in the data being filled out automatically in the text fields
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
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

        /**
         * An action listener which removes a data from the database based on the search text field
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
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

        /**
         * An action listener which adds new data into the database
         * This obtains any information from the text fields
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
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

        /**
         * An action listener which updates the data from the database
         * This results in the data being edited and changed
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
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

        /**
         * An action listener which takes the user back to the Welcome page
         */
        // CANCEL
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
                    String path = "database/print/refund.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Refund");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("refundID: " + rs.getString("refundID") + skip +
                                "refundReason: " + rs.getString("refundReason") + skip +
                                "paymentDate: " + rs.getString("paymentDate") + skip +
                                "paymentCurrency: " + rs.getString("paymentCurrency") + skip +
                                "paymentType: " + rs.getString("paymentType") + skip +
                                "saleID: " + rs.getString("saleID"));
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
            pst = main.con.prepareStatement("select * from Refund");
            ResultSet rs = pst.executeQuery();
            refundTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method which automatically updates the combo box based on the total of 'saleID' on the database
     * Recommended when using foreign keys
     */
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

