import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Refund extends JFrame {
    private JPanel mainPanel;
    private JTable refundTable;
    private JTextField IDSearchField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField IDTextField;
    private JTextField FullNameTextField;
    private JTextField TicketTextField;
    private JTextField USDTextField;
    private JTextField CardNumTextField;
    private JTextField DateTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JPanel FlavourTextLabel;
    private JButton returnButton;
    private JButton confirmButton;

    public Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(600, 400);   // window resolution
        setVisible(true);
        createTable();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search confirmed!");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Confirmed");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Confirmed!");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Update Confirmed!");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });
    }
    private void createTable(){
        refundTable.setModel(new DefaultTableModel(
                null,
                new String[]{"ID","Full Name","Ticket", "USD", "Card Number", "Date"}
        ));
        refundTable.setVisible(true);
    }
}

