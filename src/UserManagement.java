import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagement extends JFrame {
    private JButton newButton;
    private JButton deleteUserButton;
    private JButton cancelButton;
    private JButton applyButton;
    private JTextField IDTextField;
    private JTextField nameTextField;
    private JPanel mainPanel;
    private JLabel userManagementLabel;
    private JTextField textField1;
    private JTextField addressTextField;
    private JTextField dateOfBirthTextField;
    private JTextField dayJoinedTextField;
    private JComboBox typeComboBox;
    private JLabel CopyrightLabel;
    private JLabel customerIdLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel dateOfBirthLabel;
    private JLabel dayJoinedLabel;
    private JComboBox selectUserComboBox;
    private JLabel infoLabel;
    private JTextField agentIdTextField;
    private JLabel agentIdLabel;

    public UserManagement() {
        setContentPane(mainPanel);
        setTitle("ATS System");
        setSize(600, 600);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddUser addUser = new AddUser();
            }
        });
    }
}
