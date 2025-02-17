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
 * A class which allows the employees to alter the information of the Office Manager database
 * This allows the employees to create, update, search or delete an Office Manager
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
public class E1_EditOfficeManager extends JFrame {
    private JPanel mainPanel;
    private JLabel stockControlLabel;
    private JLabel managerNameLabel;
    private JLabel usernameLabel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JScrollPane SPblankTable;
    private JTable blankTable;
    private JButton searchButton;
    private JLabel infoLabel;
    private JButton deleteButton;
    private JTextField searchTextField;
    private JButton updateButton;
    private JLabel passwordLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;
    private JLabel phoneNUmberLabel;
    private JButton printButton;

    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    /**
     * A constructor which creates the GUI frame of Office Manager for the Manager
     * Includes the main panel, labels, button functionalities
     */
    public E1_EditOfficeManager() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(1000, 700);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket

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
        // SAVE
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String managerName, managerPhone, managerAddress, managerEmail, managerUsername, managerPassword;

                managerName = nameTextField.getText();
                managerPhone = phoneNumberTextField.getText();
                managerAddress = addressTextField.getText();
                managerEmail = emailTextField.getText();
                managerUsername = usernameTextField.getText();
                managerPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Office_Manager(managerName, managerPhone, managerAddress, managerEmail, managerUsername, managerPassword)values(?,?,?,?,?,?)");
                    pst.setString(1, managerName);
                    pst.setString(2, managerPhone);
                    pst.setString(3, managerAddress);
                    pst.setString(4, managerEmail);
                    pst.setString(5, managerUsername);
                    pst.setString(6, managerPassword);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    nameTextField.setText("");
                    phoneNumberTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    usernameTextField.setText("");
                    passwordTextField.setText("");
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
                    String managerID = searchTextField.getText();

                    pst = main.con.prepareStatement("select managerID, managerName, managerPhone, managerAddress, managerEmail, managerUsername, managerPassword from manager where managerID = ?");
                    pst.setString(1, managerID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String managerName = rs.getString(2);
                        String managerPhone = rs.getString(3);
                        String managerAddress = rs.getString(4);
                        String managerEmail = rs.getString(5);
                        String managerUsername = rs.getString(6);
                        String managerPassword = rs.getString(7);

                        nameTextField.setText(managerName);
                        phoneNumberTextField.setText(managerPhone);
                        addressTextField.setText(managerAddress);
                        emailTextField.setText(managerEmail);
                        usernameTextField.setText(managerUsername);
                        passwordTextField.setText(managerPassword);
                    } else {
                        nameTextField.setText("");
                        phoneNumberTextField.setText("");
                        addressTextField.setText("");
                        emailTextField.setText("");
                        usernameTextField.setText("");
                        passwordTextField.setText("");
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
                String managerID, managerName, managerPhone, managerAddress, managerEmail, managerUsername, managerPassword;

                managerID = searchTextField.getText();
                managerName = nameTextField.getText();
                managerPhone = phoneNumberTextField.getText();
                managerAddress = addressTextField.getText();
                managerEmail = emailTextField.getText();
                managerUsername = usernameTextField.getText();
                managerPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Office_Manager set managerName = ?, managerPhone = ?, managerAddress = ?, managerEmail = ?, managerUsername = ?, managerPassword = ? where managerID = ?");
                    pst.setString(1, managerName);
                    pst.setString(2, managerPhone);
                    pst.setString(3, managerAddress);
                    pst.setString(4, managerEmail);
                    pst.setString(5, managerUsername);
                    pst.setString(6, managerPassword);
                    pst.setString(7, managerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneNumberTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    usernameTextField.setText("");
                    passwordTextField.setText("");
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
                String managerID;
                managerID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Office_Manager where managerID = ?");
                    pst.setString(1,managerID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneNumberTextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    usernameTextField.setText("");
                    passwordTextField.setText("");
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
                    String path = "database/print/manager.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Office_Manager");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("managerID: " + rs.getString("managerID") + skip +
                                "managerName: " + rs.getString("managerName") + skip +
                                "managerPhone: " + rs.getString("managerPhone") + skip +
                                "managerAddress: " + rs.getString("managerAddress") + skip +
                                "managerEmail: " + rs.getString("managerEmail") + skip +
                                "managerUsername: " + rs.getString("managerUsername") + skip +
                                "managerPassword: " + rs.getString("managerPassword"));
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
            pst = main.con.prepareStatement("select * from Office_Manager");
            ResultSet rs = pst.executeQuery();
            blankTable.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

