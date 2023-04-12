import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

/**
 * The main game entry point
 * This class runs the whole program and handles the database connectivity
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */

public class Main {

    Connection con;
    PreparedStatement pst;

    /**
     * Runs the program
     * Changes the UI to be Metal for all operating systems
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());     // setting the UI to be Metal
        System.out.println("Booting up... ATS System");    // terminal output test
        Login login = new Login();  // runs program
    }

    /**
     * Establishes a database connection via url, user, pass
     * Outputs a message if it detects a database connection
     */
    public void connect() {
        // database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g21";
            String user = "in2018g21_a";
            String pass = "QOb9VqF7";
            this.con = DriverManager.getConnection(url,user,pass);
            if (this.con!=null) {
                System.out.println("Successful database connection");
            }
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This exports all the database tables into a single pdf file
     * Reference: https://www.youtube.com/watch?v=Zg7lS5sPN0M&ab_channel=jinujawadm
     */
    public void backup() {      // exports whole table into pdf
        // CUSTOMER
        String skip = "\n";
        try {
            String path = "database/backup/backup.pdf";       // where the pdf will be located
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));

            document.open();
            connect();

            pst = con.prepareStatement("select * from Customer select * from Admin");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Paragraph paragraph = new Paragraph("customerID: " + rs.getString("customerID") + skip +
                        "customerName: " + rs.getString("customerName") + skip +
                        "customerType: " + rs.getString("customerType") + skip +
                        "customerAddress: " + rs.getString("customerAddress") + skip +
                        "customerEmail: " + rs.getString("customerEmail") + skip +
                        "customerDateOfBirth: " + rs.getString("customerDateOfBirth") + skip +
                        "customerDayJoined: " + rs.getString("customerDayJoined") + skip +
                        "agentID: " + rs.getString("agentID") + skip +
                        rs.getString("adminID") + skip +
                        rs.getString("adminName") + skip +
                        rs.getString("adminPhone") + skip +
                        rs.getString("adminAddress") + skip +
                        rs.getString("adminEmail") + skip +
                        rs.getString("adminUsername") + skip +
                        rs.getString("adminPassword"));

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

        // ADMIN
        /*
        try {
            String path = "database/backup/admin.pdf";       // where the pdf will be located
            Document document2 = new Document();
            PdfWriter.getInstance(document2, new FileOutputStream(path));

            document2.open();
            connect();

            pst = con.prepareStatement("select * from Admin");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Paragraph paragraph = new Paragraph(rs.getString("adminID") + skip +
                        rs.getString("adminName") + skip +
                        rs.getString("adminPhone") + skip +
                        rs.getString("adminAddress") + skip +
                        rs.getString("adminEmail") + skip +
                        rs.getString("adminUsername") + skip +
                        rs.getString("adminPassword"));

                document2.add(paragraph);
                document2.add(new Paragraph(" " + skip));
            }
            document2.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

         */

        try {
            String path = "database/backup/sale.pdf";       // where the pdf will be located
            Document document3 = new Document();
            PdfWriter.getInstance(document3, new FileOutputStream(path));

            document3.open();
            connect();

            pst = con.prepareStatement("select * from Air_Ticket_Sale");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Paragraph paragraph = new Paragraph("SaleID: " + rs.getString("saleID") + skip +
                        rs.getString("saleType") + skip +
                        rs.getString("saleTotal") + skip +
                        rs.getString("saleCommission") + skip +
                        rs.getString("saleGrandTotal") + skip +
                        rs.getString("saleInterlineCurrencyRate") + skip +
                        rs.getString("ticketID") + skip +
                        rs.getString("agentID") + skip +
                        rs.getString("customerID"));

                document3.add(paragraph);
                document3.add(new Paragraph(" "));
            }
            document3.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // will continue with rest of the databases ...


    }
}


