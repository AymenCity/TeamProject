import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// Runs the main program

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());     // setting the UI to be Metal
        System.out.println("Booting up... ATS System");    // terminal output test
        Login login = new Login();  // runs program

        // database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g21";
            String user = "in2018g21_a";
            String pass = "QOb9VqF7";
            Connection con = DriverManager.getConnection(url,user,pass);
            if (con!=null) {
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
}
