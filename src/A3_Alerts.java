import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class A3_Alerts extends JFrame {
    private JPanel mainPanel;
    private JTable table1;
    private JLabel alertSevLabel;
    private JLabel alertDescLabel;
    private JTextField alertDescTextField;
    private JLabel alertStatusLabel;
    private JLabel dateCreatedLabel;
    private JTextField dateCreatedTextField;
    private JLabel lastModLabel;
    private JTextField lastModTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JComboBox sevComboBox;
    private JComboBox staComboBox;
    private JLabel alertsLabel;
    private JButton printButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public A3_Alerts() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(1000, 700);   // window resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        main.connect();
        load_table();

        // CANCEL
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // SAVE
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alertSeverity, alertDescription, alertStatus, dateCreated, lastModified;

                alertSeverity = sevComboBox.getSelectedItem().toString();
                alertDescription = alertDescTextField.getText();
                alertStatus = staComboBox.getSelectedItem().toString();
                dateCreated = dateCreatedTextField.getText();
                lastModified = lastModTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Alerts(alertSeverity, alertDescription, alertStatus, dateCreated, lastModified)values(?,?,?,?,?)");
                    pst.setString(1, alertSeverity);
                    pst.setString(2, alertDescription);
                    pst.setString(3, alertStatus);
                    pst.setString(4, dateCreated);
                    pst.setString(5, lastModified);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    sevComboBox.setSelectedIndex(0);
                    alertDescTextField.setText("");
                    staComboBox.setSelectedIndex(0);
                    dateCreatedTextField.setText("YYYY-MM-DD");
                    lastModTextField.setText("YYYY-MM-DD");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Alerts");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

