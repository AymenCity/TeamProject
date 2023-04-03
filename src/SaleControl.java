import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleControl {
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JLabel TitleLabel;
    private JButton deleteButton;
    private JTable table1;
    private JLabel FlavourTextLabel;
    private JTextField blankIDTextField;
    private JLabel BlankIDLabel;
    private JTextField typeTextField;
    private JLabel typeLabel;
    private JTextField numberTextField;
    private JLabel numberButton;
    private JTextField flightDateTextField;
    private JLabel flightDateButton;
    private JLabel statusButton;
    private JTextField statusTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField priceTextField;
    private JTextField flightTimeTextField;
    private JLabel priceLabel;
    private JLabel flightTimeLabel;
    private Login login;

    public SaleControl(){
        this.login = login;
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,600);   // window resolution
        setVisible(true);
    }
}
