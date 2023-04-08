import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JLabel SupportingLabel;
    private JButton CustomerManagementButton;
    private JButton stockControlButton;
    private JButton alertsButton;
    private JButton refundsButton;
    private JButton signOutButton;
    private JLabel copyrightLabel;
    private JButton salesControlButton;
    private JButton salesReportButton;
    private JButton backupButton;
    private JLabel userLabel;
    public Main main;

    public static JLabel static_label;

    public WelcomePage() {
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
        CustomerManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CustomerManagement customerManagement = new CustomerManagement();
            }
        });
        refundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Refund adminRefund = new Refund();
            }
        });
        stockControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StockControl stockControl = new StockControl();
            }
        });
        salesControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SaleControl saleControl = new SaleControl();
            }
        });
//        salesReportButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//                SaleReport saleReport = new SaleReport();
//            }
//        });
        salesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminLogin adminLogin = new AdminLogin();
            }
        });
        alertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Alerts alerts = new Alerts();
            }
        });
    }

}
