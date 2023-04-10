import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class W3_WelcomePageAgent extends JFrame {
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JLabel SupportingLabel;
    private JButton stockControlButton;
    private JButton alertsButton;
    private JButton signOutButton;
    private JButton salesControlButton;
    private JButton salesReportButton;
    private JLabel userLabel;
    private JLabel copyrightLabel;
    private JButton CustomerManagementButton;
    private JButton refundsButton;
    public Main main;

    public static JLabel static_label;

    public W3_WelcomePageAgent() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,550);   // window resolution
        setVisible(true);

        static_label = welcomeLabel;

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
        refundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                R3_Refund refund = new R3_Refund();
            }
        });
    }

}
