import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class SaleReport extends JFrame {
    private JPanel mainPanel;
    private JLabel TitleLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTable table1;
    private JLabel FlavourTextLabel;
    private JTextField timeGeneratedField;
    private JLabel timeGeneratedLabel;
    private JTextField agentIDTextField;
    private JLabel agentIDLabel;
    private JLabel saleIDLabel;
    private JTextField saleIDTextField;
    private JLabel paymentTypeLabel;
    private JTextField paymentTypeTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField airlineIDTextField;
    private JLabel airlineIDLabel;
    private JLabel saleTypeLabel;
    private JTextField saleTypeField;
    private JPanel salesReportMainPanel;
    private JTextField paymentCurrencyTextField;
    private JTextField ticketBlankTypeTextField;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public SaleReport() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 600);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket

        // SEARCH button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String reportID = searchTextField.getText();

                    pst = main.con.prepareStatement("select reportID, timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency, ticketBlankType from Air_Ticket_Sales_Report where reportID = ?");
                    pst.setString(1, reportID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true) {
                        String timeGenerated = rs.getString(2);
                        String agentID = rs.getString(3);
                        String airlineID = rs.getString(4);
                        String saleID = rs.getString(5);
                        String saleType = rs.getString(6);
                        String paymentType = rs.getString(7);
                        String paymentCurrency = rs.getString(8);
                        String ticketBlankType = rs.getString(9);

                        timeGeneratedField.setText(timeGenerated);
                        agentIDTextField.setText(agentID);
                        airlineIDTextField.setText(airlineID);
                        saleIDTextField.setText(saleID);
                        saleTypeField.setText(saleType);
                        paymentTypeTextField.setText(paymentType);
                        paymentCurrencyTextField.setText(paymentCurrency);
                        ticketBlankTypeTextField.setText(ticketBlankType);

                    } else {
                        timeGeneratedField.setText("");
                        agentIDTextField.setText("");
                        airlineIDTextField.setText("");
                        saleIDTextField.setText("");
                        saleTypeField.setText("");
                        paymentTypeTextField.setText("");
                        paymentCurrencyTextField.setText("");
                        ticketBlankTypeTextField.setText("");
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }


            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Button activated!");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saved!");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Information Updated!");
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
            pst = main.con.prepareStatement("select * from Air_Ticket_Sales_Report");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}