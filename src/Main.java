import javax.swing.*;
// Runs the main program

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());     // setting the UI to be Metal
        System.out.println("Booting up... ATS System");    // terminal output test
        Login login = new Login();  // runs program
    }
}
