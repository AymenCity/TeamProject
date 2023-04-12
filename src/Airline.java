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
 * A class which allows the employees to alter the information of the Airline database
 * This allows the employees to create, update, search or delete an Airline
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
public class Airline extends JFrame {
    private JPanel mainPanel;
    private JLabel SearchTextLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JLabel TitleLabel;
    private JTable table1;
    private JTextField phoneTextField;
    private JTextField nameTextField;
    private JPanel LowerPanel;
    private JLabel flavourTextLabel;
    private JButton updateButton;
    private JButton cancelButton;
    private JButton saveButton;
    private JButton printButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    /**
     * A constructor which creates the GUI frame of Airline
     * Includes the main panel, labels, button functionalities
     */
    public Airline() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(1000, 700);   // window resolution
        setVisible(true);

        main.connect();
        load_table();

        /**
         * An action listener which searches any data from the database from the search text field
         * This results in the data being filled out automatically in the text fields
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
        // SEARCH Button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String airlineID = searchTextField.getText();

                    pst = main.con.prepareStatement("select airlineID, airlineName, airlineContactInfo from Airline where airlineID = ?");
                    pst.setString(1, airlineID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String airlineName = rs.getString(2);
                        String airlineContactInfo = rs.getString(3);

                        nameTextField.setText(airlineName);
                        phoneTextField.setText(airlineContactInfo);
                    } else {
                        nameTextField.setText("");
                        phoneTextField.setText("");
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
        // DELETE Button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String airlineID;
                airlineID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Airline where airlineID = ?");
                    pst.setString(1, airlineID);
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
        // SAVE Button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String airlineName, airlineContactInfo;

                airlineName = nameTextField.getText();
                airlineContactInfo = phoneTextField.getText();


                try {
                    pst = main.con.prepareStatement("insert into Airline(airlineName, airlineContactInfo)values(?,?)");
                    pst.setString(1, airlineName);
                    pst.setString(2, airlineContactInfo);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    nameTextField.setText("");
                    phoneTextField.setText("");
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
        // UPDATE Button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String airlineID, airlineName, airlineContactInfo;

                airlineID = searchTextField.getText();
                airlineName = nameTextField.getText();
                airlineContactInfo = phoneTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Payment set airlineName = ?, airlineContactInfo = ? where airlineID = ?");
                    pst.setString(1, airlineName);
                    pst.setString(2, airlineContactInfo);
                    pst.setString(3, airlineID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    nameTextField.setText("");
                    phoneTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        /**
         * An action listener which takes the user back to the Welcome page
         */
        // CANCEL Button
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
        // PRINT Button
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(mainPanel, "Exporting to PDF is Successful");
                    String path = "database/print/airline.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Airline");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("airlineID: " + rs.getString("airlineID") + skip +
                                "airlineName: " + rs.getString("airlineName") + skip +
                                "airlineContactInfo: " + rs.getString("airlineContactInfo"));
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
            pst = main.con.prepareStatement("select * from Airline");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
