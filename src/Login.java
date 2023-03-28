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
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        signUpButton.addActionListener(new ActionListener() {   // replaces Login page with Signup page
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();                      // removes Login page
                Signup signup = new Signup();   // adds Signup page
            }
        });
        loginButton.addActionListener(new ActionListener() {    // detects if user credentials are correct
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();        // create string email - gets inputs from text field
                String password = passwordTextField.getText();  // create string password - gets inputs from text field

                if (email.equals("test@gmail.com") && password.equals("123"))   // login credentials are correct
                    JOptionPane.showMessageDialog(loginButton, "Login is successful!");
                    // dispose();
                    // WelcomePage welcome = new WelcomePage();
                else                                                            // login credentials are incorrect
                    JOptionPane.showMessageDialog(loginButton, "Incorrect email or password.");
            }
        });
    }
}






