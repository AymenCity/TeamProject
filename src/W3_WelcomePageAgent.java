import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class which displays a welcome page for the Travel Agent
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
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

    /**
     * A constructor which creates the GUI frame of WelcomePageAgent
     * Includes the main panel, labels, button functionalities
     */
    public W3_WelcomePageAgent() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,550);   // window resolution
        setVisible(true);

        static_label = welcomeLabel;

        /**
         * An action listener which takes the user back to Login
         */
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });

        /**
         * An action listener which takes the user to a new frame called Customer Management
         */
        CustomerManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerManagement customerManagement = new CustomerManagement();
            }
        });
        /**
         * An action listener which takes the user to a new frame called Stock Control
         */
        stockControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockControl stockControl = new StockControl();
            }
        });
        /**
         * An action listener which takes the user to a new frame called Sales Control
         */
        salesControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaleControl saleControl = new SaleControl();
            }
        });
        /**
         * An action listener which takes the user to a new frame called Sales Report
         */
        salesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                R3_SaleReport saleReport = new R3_SaleReport();
            }
        });
        /**
         * An action listener which takes the user to a new frame called Alerts
         */
        alertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                A3_Alerts a3_alerts = new A3_Alerts();
            }
        });
        /**
         * An action listener which takes the user to a new frame called Refund
         */
        refundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                R3_Refund refund = new R3_Refund();
            }
        });
    }

}
