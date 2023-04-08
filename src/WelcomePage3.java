import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Ref;

public class WelcomePage3 extends JFrame {
    private JLabel welcomeLabel;
    private JLabel flavourTextLabel;
    private JButton customerManagementButton;
    private JButton stockControlButton;
    private JButton salesControlButton;
    private JButton salesReportButton;
    private JButton refundsButton;
    private JButton alertsButton;
    private JButton backupButton;
    private JButton signOutButton;
    private JLabel copyrightLabel;
    private JPanel mainPanel;

    public WelcomePage3() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,600);   // window resolution
        setVisible(true);

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
                dispose();
                CustomerManagement customerManagement = new CustomerManagement();
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
        salesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SaleReport saleReport = new SaleReport();
            }
        });
        refundsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Refund refund = new Refund();
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
