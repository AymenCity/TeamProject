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

public class E2_EditAdmin extends JFrame {
    private JPanel parentPanel;
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
    private JTextField phoneTextField;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JLabel phoneLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JButton printButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public E2_EditAdmin() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(1000, 700);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket

        // cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // save
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminName, adminPhone, adminAddress, adminEmail, adminUsername, adminPassword;

                adminName = nameTextField.getText();
                adminPhone = phoneTextField.getText();
                adminAddress = addressTextField.getText();
                adminEmail = emailTextField.getText();
                adminUsername = usernameTextField.getText();
                adminPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Admin(adminName, adminPhone, adminAddress, adminEmail, adminUsername, adminPassword)values(?,?,?,?,?,?)");
                    pst.setString(1, adminName);
                    pst.setString(2, adminPhone);
                    pst.setString(3, adminAddress);
                    pst.setString(4, adminEmail);
                    pst.setString(5, adminUsername);
                    pst.setString(6, adminPassword);
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

        // search
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String adminID = searchTextField.getText();

                    pst = main.con.prepareStatement("select adminID, adminName, adminPhone, adminAddress, adminEmail, adminUsername, adminPassword from Admin where adminID = ?");
                    pst.setString(1, adminID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String adminName = rs.getString(2);
                        String adminPhone = rs.getString(3);
                        String adminAddress = rs.getString(4);
                        String adminEmail = rs.getString(5);
                        String adminUsername = rs.getString(6);
                        String adminPassword = rs.getString(7);

                        nameTextField.setText(adminName);
                        phoneTextField.setText(adminPhone);
                        addressTextField.setText(adminAddress);
                        emailTextField.setText(adminEmail);
                        usernameTextField.setText(adminUsername);
                        passwordTextField.setText(adminPassword);
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

        // update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminID, adminName, adminPhone, adminAddress, adminEmail, adminUsername, adminPassword;

                adminID = searchTextField.getText();
                adminName = nameTextField.getText();
                adminPhone = phoneTextField.getText();
                adminAddress = addressTextField.getText();
                adminEmail = emailTextField.getText();
                adminUsername = usernameTextField.getText();
                adminPassword = passwordTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Admin set adminName = ?, adminPhone = ?, adminAddress = ?, adminEmail = ?, adminUsername = ?, adminPassword = ? where adminID = ?");
                    pst.setString(1, adminName);
                    pst.setString(2, adminPhone);
                    pst.setString(3, adminAddress);
                    pst.setString(4, adminEmail);
                    pst.setString(5, adminUsername);
                    pst.setString(6, adminPassword);
                    pst.setString(7, adminID);
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

        // delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminID;
                adminID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Admin where adminID = ?");
                    pst.setString(1,adminID);
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
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(mainPanel, "Exporting to PDF is Successful");
                    String path = "database/print/admin.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Admin");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("adminID: " + rs.getString("adminID") + skip +
                                "adminName: " + rs.getString("adminName") + skip +
                                "adminPhone: " + rs.getString("adminPhone") + skip +
                                "adminAddress: " + rs.getString("adminAddress") + skip +
                                "adminEmail: " + rs.getString("adminEmail") + skip +
                                "adminUsername: " + rs.getString("adminUsername") + skip +
                                "adminPassword: " + rs.getString("adminPassword"));
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

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Admin");
            ResultSet rs = pst.executeQuery();
            blankTable.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
