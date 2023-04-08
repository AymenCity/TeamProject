import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JButton signUpButton;
    private JButton loginButton;
    private JLabel orLabel;
    private JLabel infoLabel;
    private JLabel copyrightLabel;
    private JCheckBox showPasswordCheckBox;
    private JPasswordField passwordField;
    private JButton clearButton;
    boolean matched = false;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public Login() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 400);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        main.connect();


        signUpButton.addActionListener(new ActionListener() {   // replaces Login page with Signup page
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();                      // removes Login page
                Signup signup = new Signup();   // adds Signup page
            }
        });


        loginButton.addActionListener(new ActionListener() {    // detects if user credentials are correct
            @Override
            public void actionPerformed(ActionEvent e) {
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
                            WelcomePageManager welcomePageManager = new WelcomePageManager();
                            WelcomePageManager.static_label.setText(usernameTextField.getText());
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
                            WelcomePageAdmin welcomePageAdmin = new WelcomePageAdmin();
                            //WelcomePageAdmin.static_label.setText(usernameTextField.getText());
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
                            WelcomePageAgent welcomePageAdmin = new WelcomePageAgent();
                            //WelcomePageAdmin.static_label.setText(usernameTextField.getText());
                        } else {
                            //JOptionPane.showMessageDialog(mainPanel, "Invalid Username / Password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }


                /*try {
                    FileReader fr = new FileReader("src/account/login.txt");
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
                    WelcomePageManager welcomePageManager = new WelcomePageManager();
                    WelcomePageManager.static_label.setText(usernameTextField.getText());

                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Username / Password", "Error", JOptionPane.ERROR_MESSAGE);
                } */
            }
        });


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
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }
}








