import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword extends JFrame{
    private JLabel emailLabel;
    private JLabel newPasswordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel confirmationCodeLabel;
    private JTextField emailTextField;
    private JTextField newPasswordTextField;
    private JTextField confirmPasswordTextField;
    private JTextField confirmationCodeTextField;
    private JButton cancelButton;
    private JButton saveButton;
    private JLabel changePasswordLabel;
    private JPanel mainPanel;
    private JLabel airViaLabel;

    public ChangePassword() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
