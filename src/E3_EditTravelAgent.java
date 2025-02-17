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
 * A class which allows the employees to alter the information of the Travel Agent database
 * This allows the employees to create, update, search or delete a Travel Agent
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
public class E3_EditTravelAgent extends JFrame {
    private JPanel mainPanel;
    private JLabel stockControlLabel;
    private JLabel nameLabel;
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
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JPanel parentPanel;
    private JLabel emailLabel;
    private JLabel addressLabel;
    private JTextField phoneTextField;
    private JLabel phoneNumberLabel;
    private JButton printButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    /**
     * A constructor which creates the GUI frame of Travel Agent for the Manager
     * Includes the main panel, labels, button functionalities
     */
    public E3_EditTravelAgent() {
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
                String agentName, agentPhone, agentAddress, agentEmail, agentUsername, agentPassword;

                agentName = nameTextField.getText();
                agentPhone = phoneTextField.getText();
                agentAddress = addressTextField.getText();
                agentEmail = emailTextField.getText();
                agentUsername = usernameTextField.getText();
                agentPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Travel_Agent(agentName, agentPhone, agentAddress, agentEmail, agentUsername, agentPassword)values(?,?,?,?,?,?)");
                    pst.setString(1, agentName);
                    pst.setString(2, agentPhone);
                    pst.setString(3, agentAddress);
                    pst.setString(4, agentEmail);
                    pst.setString(5, agentUsername);
                    pst.setString(6, agentPassword);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    nameTextField.setText("");
                    phoneTextField.setText("");
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
                    String agentID = searchTextField.getText();

                    pst = main.con.prepareStatement("select agentID, agentName, agentPhone, agentAddress, agentEmail, agentUsername, agentPassword from Travel_Agent where agentID = ?");
                    pst.setString(1, agentID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String agentName = rs.getString(2);
                        String agentPhone = rs.getString(3);
                        String agentAddress = rs.getString(4);
                        String agentEmail = rs.getString(5);
                        String agentUsername = rs.getString(6);
                        String agentPassword = rs.getString(7);

                        nameTextField.setText(agentName);
                        phoneTextField.setText(agentPhone);
                        addressTextField.setText(agentAddress);
                        emailTextField.setText(agentEmail);
                        usernameTextField.setText(agentUsername);
                        passwordTextField.setText(agentPassword);
                    } else {
                        nameTextField.setText("");
                        phoneTextField.setText("");
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
                String agentID, agentName, agentPhone, agentAddress, agentEmail, agentUsername, agentPassword;

                agentID = searchTextField.getText();
                agentName = nameTextField.getText();
                agentPhone = phoneTextField.getText();
                agentAddress = addressTextField.getText();
                agentEmail = emailTextField.getText();
                agentUsername = usernameTextField.getText();
                agentPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Travel_Agent set agentName = ?, agentPhone = ?, agentAddress = ?, agentEmail = ?, agentUsername = ?, agentPassword = ? where agentID = ?");
                    pst.setString(1, agentName);
                    pst.setString(2, agentPhone);
                    pst.setString(3, agentAddress);
                    pst.setString(4, agentEmail);
                    pst.setString(5, agentUsername);
                    pst.setString(6, agentPassword);
                    pst.setString(7, agentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneTextField.setText("");
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
                String agentID;
                agentID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Travel_Agent where agentID = ?");
                    pst.setString(1,agentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneTextField.setText("");
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
                    String path = "database/print/agent.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Travel_Agent");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("agentID: " + rs.getString("agentID") + skip +
                                "agentName: " + rs.getString("agentName") + skip +
                                "agentPhone: " + rs.getString("agentPhone") + skip +
                                "agentAddress: " + rs.getString("agentAddress") + skip +
                                "agentEmail: " + rs.getString("agentEmail") + skip +
                                "agentUsername: " + rs.getString("agentUsername") + skip +
                                "agentPassword: " + rs.getString("agentPassword"));
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
            pst = main.con.prepareStatement("select * from Travel_Agent");
            ResultSet rs = pst.executeQuery();
            blankTable.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
