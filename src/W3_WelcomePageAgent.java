import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class W3_WelcomePageAgent extends JFrame {
    private JPanel mainPanel;
    private JLabel flavourTextLabel;
    private JLabel welcomeLabel;
    private JLabel SupportingLabel;
    private JButton customerManagementButton;
    private JButton stockControlButton;
    private JButton alertsButton;
    private JButton refundButton;
    private JButton signOutButton;
    private JLabel copyrightLabel;
    private JButton salesControlButton;
    private JButton salesReportButton;
    private JButton backupButton;
    private JLabel userLabel;
    private JButton travelAgentDetailsButton;
    private JButton systemAdminstratorDetailsButton;
    private JButton managerDetailsButton;
    public Main main;

    public static JLabel static_label;

    public W3_WelcomePageAgent() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,600);   // window resolution
        setVisible(true);

        static_label = userLabel;

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });

        customerManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerManagement customerManagement = new CustomerManagement();
            }
        });
        stockControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockControl stockControl = new StockControl();
            }
        });
        salesControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaleControl saleControl = new SaleControl();
            }
        });
        salesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaleReport saleReport = new SaleReport();
            }
        });
        alertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alerts alerts = new Alerts();
            }
        });
        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                R3_Refund refund = new R3_Refund();
            }
        });
    }

}
