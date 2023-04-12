import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * A class which detects user credential details from the database
 * @author Aymen Said, Rati Sturua, Ethan Brewer
 * @version 134
 */
public class Login extends JFrame {
    private JPanel mainPanel;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JLabel infoLabel;
    private JLabel copyrightLabel;
    private JLabel SupportingLabel;
    private JCheckBox showPasswordCheckBox;
    private JPasswordField passwordField;
    private JButton clearButton;
    boolean matched = false;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    /**
     * A constructor which creates the GUI frame of Login
     * Includes the main panel, labels, button functionalities
     */
    public Login() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 500);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        main.connect();


        /**
         * An action listener for Login button
         * This calls the method of "detectsLogin"
         * Method is called when the user clicks on the Login button
         */
        loginButton.addActionListener(new ActionListener() {    // detects if user credentials are correct
            @Override
            public void actionPerformed(ActionEvent e) {
                detectsLogin();
            }
        });

        /**
         * An action listener for password check box
         * This allows the user to hide/show their password
         * Reference: https://www.youtube.com/watch?v=MXT1jFmLm80&ab_channel=KensoftPH
         */
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u25cf');
                }

            }
        });

        /**
         * A key listener for password field
         * This calls the method of "detectsLogin"
         * Method is called when the user clicks on the enter key in the password field
         * Reference: https://www.youtube.com/watch?v=ktpeW2m4rtU&ab_channel=ProgrammingKnowledge
         */
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    detectsLogin();
                }
            }

        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    /**
     * This method detects if the user credentials are correct based on the database
     * Displays a different welcome page based on the different types of role (agent, admin, manager)
     * Reference: https://www.youtube.com/watch?v=hO3VQ3yipQM&ab_channel=StudyViral
     */
    private void detectsLogin() {
        String managerUsername, managerPassword, adminUsername, adminPassword, agentUsername, agentPassword;
        managerUsername = usernameTextField.getText().trim();
        managerPassword = passwordField.getText().trim();
        adminUsername = usernameTextField.getText().trim();
        adminPassword = passwordField.getText().trim();
        agentUsername = usernameTextField.getText().trim();
        agentPassword = passwordField.getText().trim();

        // MANAGER
        if (managerUsername.equals("") || managerPassword.equals("")) {
            JOptionPane.showMessageDialog(mainPanel, "Some Fields are Empty", "Error", 1);
        } else {
            try {
                pst = main.con.prepareStatement("select * from Office_Manager where managerUsername = ? and managerPassword = ?");
                pst.setString(1, managerUsername);
                pst.setString(2, managerPassword);
                ResultSet rs = pst.executeQuery();

                if(rs.next() == true) {
                    dispose();
                    W1_WelcomePageManager welcomePageManager = new W1_WelcomePageManager();
                    W1_WelcomePageManager.static_label.setText("Welcome, "+usernameTextField.getText());
                    W1_WelcomePageManager.static_label.setForeground(Color.WHITE);
                } else {
                    //JOptionPane.showMessageDialog(mainPanel, "Invalid Username / Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // ADMIN
        if (adminUsername.equals("") || adminPassword.equals("")) {
            JOptionPane.showMessageDialog(mainPanel, "Some Fields are Empty", "Error", 1);
        } else {
            try {
                pst = main.con.prepareStatement("select * from Admin where adminUsername = ? and adminPassword = ?");
                pst.setString(1, adminUsername);
                pst.setString(2, adminPassword);
                ResultSet rs = pst.executeQuery();

                if(rs.next() == true) {
                    dispose();
                    W2_WelcomePageAdmin w1WelcomePageAdmin = new W2_WelcomePageAdmin();
                    W2_WelcomePageAdmin.static_label.setText("Welcome, "+usernameTextField.getText());
                    W2_WelcomePageAdmin.static_label.setForeground(Color.WHITE);
                } else {
                    //JOptionPane.showMessageDialog(mainPanel, "Invalid Username / Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // AGENT
        if (agentUsername.equals("") || agentPassword.equals("")) {
            JOptionPane.showMessageDialog(mainPanel, "Some Fields are Empty", "Error", 1);
        } else {
            try {
                pst = main.con.prepareStatement("select * from Travel_Agent where agentUsername = ? and agentPassword = ?");
                pst.setString(1, agentUsername);
                pst.setString(2, agentPassword);
                ResultSet rs = pst.executeQuery();

                if(rs.next() == true) {
                    dispose();
                    W3_WelcomePageAgent welcomePageAdmin = new W3_WelcomePageAgent();
                    W3_WelcomePageAgent.static_label.setText("Welcome, "+usernameTextField.getText());
                    W3_WelcomePageAgent.static_label.setForeground(Color.WHITE);
                } else {
                    //JOptionPane.showMessageDialog(mainPanel, "Invalid Username / Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}








