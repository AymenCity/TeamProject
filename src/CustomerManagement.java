import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A class which allows the employees to alter the information of the Customer database
 * This allows the employees to create, update, search or delete a customer
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
public class CustomerManagement extends JFrame {
    private JButton newButton;
    private JButton cancelButton;
    private JButton addNewCustomerButton;
    private JTextField nameTextField;
    private JPanel mainPanel;
    private JLabel userManagementLabel;
    private JTextField addressTextField;
    private JTextField dateOfBirthTextField;
    private JTextField dayJoinedTextField;
    private JComboBox typeComboBox;
    private JLabel CopyrightLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel dateOfBirthLabel;
    private JLabel dayJoinedLabel;
    private JLabel infoLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTable table1;
    private JButton updateButton;
    private JTextField emailTextField;
    private JComboBox agentIDComboBox;
    private JLabel agentIDLabel;
    private JButton printButton;
    private JPanel dateOfBirthCalandar;

    Calendar cal = Calendar.getInstance();
    JDateChooser dateChooser = new JDateChooser();
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    /**
     * A constructor which creates the GUI frame of Customer Management
     * Includes the main panel, labels, button functionalities
     */
    public CustomerManagement() {
        setContentPane(mainPanel);
        setTitle("ATS System");
        setSize(1000, 700);
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket
        update_AgentComboBox();

        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateOfBirthCalandar.add(dateChooser);



        dayJoinedTextField.setText("YYYY-MM-DD");

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
         * An action listener which adds new data into the database
         * This obtains any information from the text fields
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
        // ADD (SAVE)
        addNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName, customerType, customerAddress, customerEmail, customerDateOfBirth, customerDayJoined, agentID;

                customerName = nameTextField.getText();
                customerType = typeComboBox.getSelectedItem().toString();
                customerAddress = addressTextField.getText();
                customerEmail = emailTextField.getText();
                customerDateOfBirth = dateOfBirthTextField.getText();
                customerDayJoined = dayJoinedTextField.getText();
                agentID = agentIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("insert into Customer(customerName, customerType, customerAddress, customerEmail, customerDateOfBirth, customerDayJoined, agentID)values(?,?,?,?,?,?,?)");
                    pst.setString(1, customerName);
                    pst.setString(2, customerType);
                    pst.setString(3, customerAddress);
                    pst.setString(4, customerEmail);
                    pst.setString(5, customerDateOfBirth);
                    pst.setString(6, customerDayJoined);
                    pst.setString(7, agentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    typeComboBox.setSelectedIndex(0);
                    nameTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    dateOfBirthTextField.setText("YYYY-MM-DD");
                    dayJoinedTextField.setText("YYYY-MM-DD");
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
        // SEARCH
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String customerID = searchTextField.getText();

                    pst = main.con.prepareStatement("select customerID, customerName, customerType, customerAddress, customerEmail, customerDateOfBirth, customerDayJoined, agentID from Customer where customerID = ?");
                    pst.setString(1, customerID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String customerName = rs.getString(2);
                        String customerType = rs.getString(3);
                        String customerAddress = rs.getString(4);
                        String customerEmail = rs.getString(5);
                        String customerDateOfBirth = rs.getString(6);
                        String customerDayJoined = rs.getString(7);
                        String agentID = rs.getString(8);

                        nameTextField.setText(customerName);
                        typeComboBox.setSelectedItem(customerType);
                        addressTextField.setText(customerAddress);
                        emailTextField.setText(customerEmail);
                        dateOfBirthTextField.setText(customerDateOfBirth);
                        dayJoinedTextField.setText(customerDayJoined);
                        agentIDComboBox.setSelectedItem(agentID);
                    } else {
                        typeComboBox.setSelectedIndex(0);
                        nameTextField.setText("");
                        addressTextField.setText("");
                        emailTextField.setText("");
                        dateOfBirthCalandar.removeAll();
                        dayJoinedTextField.setText("YYYY-MM-DD");
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
        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerID, customerName, customerType, customerAddress, customerEmail, customerDateOfBirth, customerDayJoined, agentID;

                customerID = searchTextField.getText();
                customerName = nameTextField.getText();
                customerType = typeComboBox.getSelectedItem().toString();
                customerAddress = addressTextField.getText();
                customerEmail = emailTextField.getText();
                customerDateOfBirth = dateOfBirthTextField.getText();
                customerDayJoined = dayJoinedTextField.getText();
                agentID = agentIDComboBox.getSelectedItem().toString();

                try {
                    pst = main.con.prepareStatement("update Customer set customerName = ?, customerType = ?, customerAddress = ?, customerEmail = ?, customerDateOfBirth = ?, customerDayJoined = ?, agentID = ? where customerID = ?");
                    pst.setString(1, customerName);
                    pst.setString(2, customerType);
                    pst.setString(3, customerAddress);
                    pst.setString(4, customerEmail);
                    pst.setString(5, customerDateOfBirth);
                    pst.setString(6, customerDayJoined);
                    pst.setString(7, agentID);
                    pst.setString(8, customerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    nameTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    dateOfBirthTextField.setText("YYYY-MM-DD");
                    dayJoinedTextField.setText("YYYY-MM-DD");
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
        // DELETE Button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerID;
                customerID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Customer where customerID = ?");
                    pst.setString(1,customerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    searchTextField.setText("");
                    typeComboBox.setSelectedIndex(0);
                    nameTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    dateOfBirthTextField.setText("YYYY-MM-DD");
                    dayJoinedTextField.setText("YYYY-MM-DD");
                    agentIDComboBox.setSelectedIndex(0);
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
                    String path = "database/print/customer.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Customer");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("customerID: " + rs.getString("customerID") + skip +
                                "customerName: " + rs.getString("customerName") + skip +
                                "customerType: " + rs.getString("customerType") + skip +
                                "customerAddress: " + rs.getString("customerAddress") + skip +
                                "customerEmail: " + rs.getString("customerEmail") + skip +
                                "customerDateOfBirth: " + rs.getString("customerDateOfBirth") + skip +
                                "customerDayJoined: " + rs.getString("customerDayJoined") + skip +
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
            pst = main.con.prepareStatement("select * from Customer");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
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
