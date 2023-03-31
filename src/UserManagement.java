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
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox roleComboBox;
    private JLabel CopyrightLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JComboBox selectUserComboBox;
    private JLabel infoLabel;

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
