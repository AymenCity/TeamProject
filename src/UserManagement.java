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

    public UserManagement() {
        setContentPane(mainPanel);
        setTitle("ATS System");
        setSize(600, 400);
        setVisible(true);
    }
}
