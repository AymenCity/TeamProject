import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class which displays a welcome page for the Manager
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */

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

    /**
     * A constructor which creates the GUI frame of WelcomePageManager
     * Includes the main panel, labels, button functionalities
     */
    public W1_WelcomePageManager() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,700);   // window resolution
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
         * An action listener which takes the user to a new frame called Refund
         */
        refundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                R1_Refund refund = new R1_Refund();
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
                R1_SaleReport saleReport = new R1_SaleReport();
            }
        });

        /**
         * An action listener which takes the user to a new frame called Alerts
         */
        alertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                A1_Alerts a1Alerts = new A1_Alerts();
            }
        });

        /**
         * An action listener which takes the user to a new frame called Edit Travel Agent
         */
        travelAgentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                E3_EditTravelAgent travelAgent = new E3_EditTravelAgent();
            }
        });

        /**
         * An action listener which takes the user to a new frame called Edit Travel Admin
         */
        systemAdminstratorDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                E2_EditAdmin admin = new E2_EditAdmin();
            }
        });

        /**
         * An action listener which takes the user to a new frame called Edit Manager
         */
        managerDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                E1_EditOfficeManager officeManager = new E1_EditOfficeManager();
            }
        });

        /**
         * An action listener which calls the method from main
         * Displays a message which lets the user know that the backup is successful
         */
        backupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainPanel, "Backup is Successful");
                Main main = new Main();
                main.backup();
            }
        });

        /**
         * An action listener which takes the user to a new frame called Airline
         */
        airlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Airline airline = new Airline();
            }
        });
    }

}
