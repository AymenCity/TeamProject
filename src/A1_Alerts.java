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
 * A class which allows the employees to alter the information of the Alerts database
 * This allows the employees to create, update, search or delete an Alert
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
public class A1_Alerts extends JFrame {


    private JLabel alertsLabel;
    private JTable table1;
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JLabel alertSevLabel;
    private JLabel flavourTextLabel;
    private JTextField alertDescTextField;
    private JLabel alertDescLabel;
    private JLabel alertStatusLabel;
    private JLabel dateCreatedLabel;
    private JTextField dateCreatedTextField;
    private JLabel lastModLabel;
    private JTextField lastModTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton printButton;
    private JButton cancelButton;
    private JComboBox sevComboBox;
    private JComboBox staComboBox;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    /**
     * A constructor which creates the GUI frame of Alerts for the Manager
     * Includes the main panel, labels, button functionalities
     */
    public A1_Alerts() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(1000,700);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        main.connect();
        load_table();

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
         * An action listener which searches any data from the database from the search text field
         * This results in the data being filled out automatically in the text fields
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
        // SEARCH
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String alertID = searchTextField.getText();

                    pst = main.con.prepareStatement("select alertID, alertSeverity, alertDescription, alertStatus, dateCreated, lastModified from Alerts where alertID = ?");
                    pst.setString(1, alertID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String alertSeverity = rs.getString(2);
                        String alertDescription = rs.getString(3);
                        String alertStatus = rs.getString(4);
                        String dateCreated = rs.getString(5);
                        String lastModified = rs.getString(6);

                        sevComboBox.setSelectedItem(alertSeverity);
                        alertDescTextField.setText(alertDescription);
                        staComboBox.setSelectedItem(alertStatus);
                        dateCreatedTextField.setText(dateCreated);
                        lastModTextField.setText(lastModified);
                    } else {
                        sevComboBox.setSelectedIndex(0);
                        alertDescTextField.setText("");
                        staComboBox.setSelectedItem(0);
                        dateCreatedTextField.setText("YYYY-MM-DD");
                        lastModTextField.setText("YYYY-MM-DD");
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
                String alertID;
                alertID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Alerts where alertID = ?");
                    pst.setString(1,alertID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    sevComboBox.setSelectedIndex(0);
                    alertDescTextField.setText("");
                    staComboBox.setSelectedItem(0);
                    dateCreatedTextField.setText("YYYY-MM-DD");
                    lastModTextField.setText("YYYY-MM-DD");
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
                String alertSeverity, alertDescription, alertStatus, dateCreated, lastModified;

                alertSeverity = sevComboBox.getSelectedItem().toString();
                alertDescription = alertDescTextField.getText();
                alertStatus = staComboBox.getSelectedItem().toString();
                dateCreated = dateCreatedTextField.getText();
                lastModified = lastModTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Alerts(alertSeverity, alertDescription, alertStatus, dateCreated, lastModified)values(?,?,?,?,?)");
                    pst.setString(1, alertSeverity);
                    pst.setString(2, alertDescription);
                    pst.setString(3, alertStatus);
                    pst.setString(4, dateCreated);
                    pst.setString(5, lastModified);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    sevComboBox.setSelectedIndex(0);
                    alertDescTextField.setText("");
                    staComboBox.setSelectedIndex(0);
                    dateCreatedTextField.setText("YYYY-MM-DD");
                    lastModTextField.setText("YYYY-MM-DD");
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
                String alertID, alertSeverity, alertDescription, alertStatus, dateCreated, lastModified;

                alertID = searchTextField.getText();
                alertSeverity = sevComboBox.getSelectedItem().toString();
                alertDescription = alertDescTextField.getText();
                alertStatus = staComboBox.getSelectedItem().toString();
                dateCreated = dateCreatedTextField.getText();
                lastModified = lastModTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Alerts set alertSeverity = ?, alertDescription = ?, alertStatus = ?, dateCreated = ?, lastModified = ? where alertID = ?");
                    pst.setString(1, alertSeverity);
                    pst.setString(2, alertDescription);
                    pst.setString(3, alertStatus);
                    pst.setString(4, dateCreated);
                    pst.setString(5, lastModified);
                    pst.setString(6, alertID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    sevComboBox.setSelectedIndex(0);
                    alertDescTextField.setText("");
                    staComboBox.setSelectedIndex(0);
                    dateCreatedTextField.setText("YYYY-MM-DD");
                    lastModTextField.setText("YYYY-MM-DD");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        /**
         * An action listener which exports a database into a pdf file
         * Reference: https://www.youtube.com/watch?v=Zg7lS5sPN0M&ab_channel=jinujawadm
         */
        // PRINT Button
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(mainPanel, "Exporting to PDF is Successful");
                    String path = "database/print/alerts.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Alerts");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("alertID: " + rs.getString("alertID") + skip +
                                "alertSeverity: " + rs.getString("alertSeverity") + skip +
                                "alertDescription: " + rs.getString("alertDescription") + skip +
                                "alertStatus: " + rs.getString("alertStatus") + skip +
                                "dateCreated: " + rs.getString("dateCreated") + skip +
                                "lastModified: " + rs.getString("lastModified"));
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
            pst = main.con.prepareStatement("select * from Alerts");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
