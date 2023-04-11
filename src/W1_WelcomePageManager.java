import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class W1_WelcomePageManager extends JFrame {
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
    private JButton travelAgentDetailsButton;
    private JButton systemAdminstratorDetailsButton;
    private JButton managerDetailsButton;
    private JButton airlineButton;
    public Main main;

    public static JLabel static_label;

    public W1_WelcomePageManager() {
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
        refundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                R1_Refund refund = new R1_Refund();
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
                R1_SaleReport saleReport = new R1_SaleReport();
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
        backupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainPanel, "Backup is Successful");
                Main main = new Main();
                main.backup();
            }
        });
        airlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Airline airline = new Airline();
            }
        });
    }

}
