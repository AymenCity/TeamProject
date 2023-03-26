import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JLabel loginLabel;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel passwordLabel;
    private JTextField passwordTextField;
    private JButton signUpButton;
    private JButton loginButton;
    private JLabel orLabel;
    private JLabel infoLabel;

    public Login() {
        setContentPane(mainPanel);
        setTitle("ATS System");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Signup signup = new Signup();
            }
        });
    }

    public static void main(String[] args) {
        System.out.println("Booting up... ATS System");
        Login login = new Login();
    }
}






