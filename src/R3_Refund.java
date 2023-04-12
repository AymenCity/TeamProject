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
        setSize(600, 700);   // window resolution
        setVisible(true);

        main.connect();
        load_table();

        /**
         * An action listener which removes a data from the database based on the search text field
         * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
         */
        // DELETE
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String refundID;
                refundID = SearchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Refund where refundID = ?");
                    pst.setString(1, refundID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Refund Accepted!");
                    load_table();
                    SearchTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        /**
         * An action listener which takes the user back to the Welcome page
         */
        // CANCEL
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * A method which loads the data from the database
     * Reference: https://www.youtube.com/watch?v=e3AKnrTxFFo
     */
    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Refund");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
