import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleControl extends JFrame {
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JLabel TitleLabel;
    private JButton deleteButton;
    private JTable table1;
    private JLabel FlavourTextLabel;
    private JTextField saleIDTextField;
    private JLabel saleIDLabel;
    private JLabel saleTypeLabel;
    private JTextField saleTotalTextField;
    private JLabel saleTotalButton;
    private JTextField grandTotTextField;
    private JLabel grandTotButton;
    private JLabel tickIDButton;
    private JTextField tickIDTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField commisTextField;
    private JTextField currRateTextField;
    private JLabel commisLabel;
    private JLabel currRateLabel;
    private JTextField agentIDTextField;
    private JTextField custIDTextField;
    private JTextField saleTypeTextField;
    private JLabel AgentIDLabel;
    private JLabel custIDLabel;
    private JScrollPane saleControlTable;

    public SaleControl(){
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,600);   // window resolution
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
