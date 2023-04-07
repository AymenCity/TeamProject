import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Refund extends JFrame {
    private JPanel mainPanel;
    private JTable refundTable;
    private JTextField IDSearchField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField FullNameTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JPanel FlavourTextLabel;
    private JComboBox typeComboBox;
    private JComboBox stateComboBox;
    private JComboBox saleIDComboBox;
    private JTextField currencyTextField;
    private JButton returnButton;
    private JButton confirmButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);

        main.connect();
        load_table();
        update_SaleComboBox();

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

    void load_table() {
        try {
            pst = main.con.prepareStatement("select * from Payment");
            ResultSet rs = pst.executeQuery();
            refundTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update_SaleComboBox() {
        try {
            pst = main.con.prepareStatement("select * from Air_Ticket_Sale");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                saleIDComboBox.addItem(rs.getString("saleID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

