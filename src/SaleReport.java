import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleReport extends JFrame {
    private JPanel mainPanel;
    private JLabel TitleLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTable table1;
    private JLabel FlavourTextLabel;
    private JTextField reportIDTextField;
    private JLabel reportIDLabel;
    private JTextField timeGeneratedField;
    private JLabel timeGeneratedLabel;
    private JTextField agentIDTextField;
    private JLabel agentIDLabel;
    private JLabel saleIDLabel;
    private JTextField saleIDTextField;
    private JLabel paymentTypeLabel;
    private JTextField paymentTypeTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField airlineIDTextField;
    private JLabel airlineIDLabel;
    private JLabel saleTypeLabel;
    private JTextField saleTypeField;
    private JPanel salesReportMainPanel;
    private JTextField paymentCurrencyTextField;
    private JTextField ticketBlankTypeTextField;

    public SaleReport() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search Button activated!");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Button activated!");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saved!");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Information Updated!");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });
    }
}