import javax.swing.*;

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
    }
}