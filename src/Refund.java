import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Refund extends JFrame {
    private JPanel mainPanel;
    private JList CustomerList;
    private JTable refundTable;
    private JButton returnButton;
    private JButton confirmButton;
    private JLabel UserLabel;

    public Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(600, 400);   // window resolution
        setVisible(true);
        createTable();

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Refund Confirmed!");
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

