import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame {
    private JPanel mainPanel;
    private JLabel signupLabel;
    private JLabel infoLabel;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JButton signUpButton;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JRadioButton iAcceptTheTermsRadioButton;
    private JButton cancelButton;

    public Signup() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {       // replaces Signup page with Login page
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();                  // removes Signup page
                Login login = new Login();  // adds Login page
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(signUpButton, "Hello, " + nameTextField.getText() + "! You have created an account!");
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}

