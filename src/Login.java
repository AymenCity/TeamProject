import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

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
    private JButton clearButton;
    boolean matched = false;

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
                String username, password;
                username = usernameTextField.getText().trim();
                password = passwordTextField.getText().trim();

                try {
                    FileReader fr = new FileReader("src/account/login.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.equals(username+","+password)) {
                            matched = true;
                            break;
                        }
                    }
                    fr.close();
                }
                catch (Exception ex) {
                }

                if (matched) {
                    dispose();
                    WelcomePage welcomePage = new WelcomePage();
                    welcomePage.static_label.setText(usernameTextField.getText());
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Username / Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    public JPanel getMainPanel(){
        return mainPanel;
    }

}






