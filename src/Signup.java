import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JDialog {
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
        setTitle("ATS System");
        setSize(600,300);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}

