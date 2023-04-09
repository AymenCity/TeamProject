import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class R3_Refund extends JFrame {
    private JTextField SearchTextField;
    private JLabel TitleLabel;
    private JLabel SearchTextLabel;
    private JButton deleteButton;
    private JTable table1;
    private JButton cancelButton;
    private JPanel mainPanel;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public R3_Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);

        main.connect();
        load_table();


        // DELETE
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paymentID;
                paymentID = SearchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Payment where paymentID = ?");
                    pst.setString(1, paymentID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Refund Accepted!");
                    load_table();
                    SearchTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // CANCEL
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Payment");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
