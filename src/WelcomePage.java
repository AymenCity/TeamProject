import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JLabel TestNameLabel;
    private JLabel SupportingLabel;
    private JButton UserManagementButton;
    private JButton ReportButton;
    private JButton AlertsButton;
    private JButton RefundsButton;
    private JButton signOutButton;
    private JLabel copyrightLabel;

    public WelcomePage() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setVisible(true);

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });
        UserManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserManagement userManagement = new UserManagement();
            }
        });
    }
}
