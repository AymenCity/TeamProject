import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Alerts extends JFrame {


    private JLabel alertsLabel;
    private JTable table1;
    private JTextField searchTextField;
    private JComboBox typeComboBox;
    private JLabel severityTypeLabel;
    private JPanel mainPanel;
    private JButton printButton;
    private JButton cancelButton;

    public Alerts() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Print Prompted!");

            }
        });

    }

}
