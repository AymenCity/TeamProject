import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JLabel SupportingLabel;
    private JButton UserManagementButton;
    private JButton stockControlButton;
    private JButton alertsButton;
    private JButton refundsButton;
    private JButton signOutButton;
    private JLabel copyrightLabel;
    private JButton salesControlButton;
    private JButton salesReportButton;
    private JButton backupButton;
    private Login login;

    public WelcomePage() {
        this.login = login;
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,600);   // window resolution
        setVisible(true);

        // TestNameLabel.setText(login.getUsernameTextField().toString());



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
        refundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Refund refund = new Refund();
            }
        });
        stockControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StockControl stockControl = new StockControl();
            }
        });
    }

}
