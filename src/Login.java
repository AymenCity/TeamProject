import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JTextField passwordTextField;
    private JButton signUpButton;
    private JButton loginButton;
    private JLabel orLabel;
    private JLabel infoLabel;
    private JLabel copyrightLabel;

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
                String usernameT = usernameTextField.getText();
                String passwordT = passwordTextField.getText();

                try {
                    File loginFile = new File("login.txt");
                    Scanner scan = new Scanner(loginFile);
                    scan.useDelimiter("[,\n]");

                    while (scan.hasNext()) {
                        String username = scan.next();
                        String password = scan.next();

                        if (username.equals(username.trim()) && password.equals(password.trim())) {
                            dispose();
                            WelcomePage welcomePage = new WelcomePage();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "invalid");
                        }
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "invalid" +ex);
                }




            }
        });
    }


    public JPanel getMainPanel(){
        return mainPanel;
    }
    public JTextField getEmailTextField() {
        return usernameTextField;
    }
}






