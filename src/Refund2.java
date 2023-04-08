import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Refund2 extends JFrame{
    private JTextField SearchTextField;
    private JLabel TitleLabel;
    private JLabel SearchTextLabel;
    private JButton searchButton;
    private JButton deleteButton;
    private JTable table1;
    private JTextField amountTextField;
    private JLabel amountLabel;
    private JTextField currencyTextField;
    private JLabel currencyText;
    private JLabel typeLabel;
    private JLabel stateLabel;
    private JComboBox typeComboBox;
    private JComboBox stateComboBox;
    private JLabel saleIDLabel;
    private JComboBox saleIDComboBox;
    private JButton cancelButton;

    public Refund2() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search Complete!");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Complete!");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomePage2 welcomePage2 = new WelcomePage2();
            }
        });
    }
}
