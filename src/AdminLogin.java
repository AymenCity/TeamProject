import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class AdminLogin extends JFrame {
    private JPanel backPanel;
    private JPanel mainPanel;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JLabel infoLabel;
    private JLabel loginLabel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JLabel passwordLabel;
    private JLabel copyrightLabel;
    boolean matched = false;

    public AdminLogin() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {    // detects if user credentials are correct
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText().trim();
                String password = passwordTextField.getText().trim();

                try {
                    FileReader fr = new FileReader("src/account/adminLogin.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.equals(username+","+password)) {
                            matched = true;
                            break;
                        }
                    }
                    fr.close();
                }
                catch (Exception ex) {
                }

                if (matched) {
                    dispose();
                    SaleReport saleReport = new SaleReport();
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Username / Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

}

