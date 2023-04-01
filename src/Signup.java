import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class Signup extends JFrame {
    private JPanel mainPanel;
    private JLabel signupLabel;
    private JLabel infoLabel;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JButton signUpButton;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JRadioButton iAcceptTheTermsRadioButton;
    private JButton cancelButton;
    private JLabel copyrightLabel;
    private JTextField confirmPasswordTextField;
    private JLabel confirmPasswordLabel;
    private JButton clearButton;

    public Signup() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {       // replaces Signup page with Login page
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();                  // removes Signup page
                Login login = new Login();  // adds Login page
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameTextField.getText();
                // String email = emailTextField.getText();
                String password = passwordTextField.getText();

                try
                {
                    FileWriter writer = new FileWriter("src/account/login.txt", true);
                    writer.write(""+username+","+password+"\n");
                    // writer.write(System.getProperty("line.separator"));
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Registration Complete");
                    dispose();
                    Login login = new Login();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "error");

                };
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTextField.setText("");
                emailTextField.setText("");
                passwordTextField.setText("");
                confirmPasswordTextField.setText("");
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
    public JTextField getNameTextField() {
        return nameTextField;
    }
}

