import javax.swing.*;

public class UserManagement extends JFrame {
    private JButton newButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JButton applyButton;
    private JTextField IDTextField;
    private JTextField nameTextField;
    private JTextField roleTextField;
    private JPanel mainPanel;
    private JLabel userManagementLabel;
    private JLabel IDLabel;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public UserManagement() {
        setContentPane(mainPanel);
        setTitle("ATS System");
        setSize(600, 600);
        setVisible(true);
    }
}
