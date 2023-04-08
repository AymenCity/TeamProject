import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Alerts extends JFrame {


    private JLabel alertsLabel;
    private JTable table1;
    private JPanel mainPanel;
    private JTextField alertSearchField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField alertIDTextField;
    private JTextField alertSevTextField;
    private JLabel alertSevLabel;
    private JLabel alertIDLabel;
    private JLabel flavourTextLabel;
    private JTextField alertDescTextField;
    private JLabel alertDescLabel;
    private JLabel alertStatusLabel;
    private JTextField alertStatusTextField;
    private JLabel dateCreatedLabel;
    private JTextField dateCreatedTextField;
    private JLabel lastModLabel;
    private JTextField lastModTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton printButton;
    private JButton cancelButton;

    public Alerts() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600,400);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search done!");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Complete!");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Done!");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Updated!");
            }
        });
    }

}
