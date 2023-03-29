import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUser extends JFrame {
    private JPanel mainPanel;
    private JLabel fullNameLabel;
    private JTextField fullNameTextField;
    private JLabel roleLabel;
    private JComboBox roleComboBox;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JButton saveButton;
    private JTextField addressTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JTextField confirmPasswordTextField;
    private JButton cancelButton;
    private JTree tree1;

    public AddUser() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserManagement userManagement = new UserManagement();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //Link To Database (Details saved in the Database)
            }
        });
    }
}
