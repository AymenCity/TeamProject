import javax.swing.*;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JLabel loginLabel;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel passwordLabel;
    private JTextField passwordTextField;
    private JButton signupButton;
    private JButton loginButton;
    private JLabel orLabel;
    private JLabel infoLabel;

    public static void main(String[] args) {
        System.out.println("Booting up... ATS System");
        Login login = new Login();
        login.setContentPane(login.mainPanel);
        login.setTitle("ATS System");
        login.setSize(600,300);
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}






