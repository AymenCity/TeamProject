import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class W2_WelcomePageAdmin extends JFrame {
    private JLabel welcomeLabel;
    private JButton CustomerManagementButton;
    private JButton stockControlButton;
    private JButton salesControlButton;
    private JButton salesReportButton;
    private JButton refundsButton;
    private JButton alertsButton;
    private JButton signOutButton;
    private JPanel mainPanel;
    private JButton travelAgentDetailsButton;
    private JButton managerDetailsButton;
    private JButton systemAdminstratorDetailsButton;
    private JLabel SupportingLabel;
    private JLabel copyrightLabel;
    private JButton backupButton;

    public static JLabel static_label;

    public W2_WelcomePageAdmin() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,700);   // window resolution
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
        refundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                R1_Refund refund = new R1_Refund();
            }
        });
        alertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                A1_Alerts a1Alerts = new A1_Alerts();
            }
        });
        travelAgentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                E3_EditTravelAgent travelAgent = new E3_EditTravelAgent();
            }
        });
        systemAdminstratorDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                E2_EditAdmin admin = new E2_EditAdmin();
            }
        });
        managerDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                E1_EditOfficeManager officeManager = new E1_EditOfficeManager();
            }
        });
    }
    }

