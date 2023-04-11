import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;


public class R1_SaleReport extends JFrame {
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JTable table1;
    private JTextField timeGeneratedField;
    private JTextField agentIDTextField;
    private JTextField saleIDTextField;
    private JTextField paymentTypeTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField airlineIDTextField;
    private JTextField saleTypeField;
    private JPanel salesReportMainPanel;
    private JTextField paymentCurrencyTextField;
    private JTextField rTextField;
    private JComboBox StypeComboBox;
    private JComboBox PtypeComboBox;

    private JComboBox agentIDComboBox;
    private JComboBox airlineIDComboBox;
    private JComboBox saleIDComboBox;
    private JLabel infoLabel;
    private JLabel timeGeneratedLabel;
    private JLabel agentIDLabel;
    private JLabel saleIDLabel;
    private JLabel paymentTypeLabel;
    private JLabel airlineIDLabel;
    private JLabel saleTypeLabel;
    private JLabel TitleLabel;
    private JButton printButton;
    Connection con;
    PreparedStatement pst;
    Main main = new Main();

    public R1_SaleReport() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(1000, 750);   // window resolution
        setVisible(true);

        main.connect(); // calling database connection from Main
        load_table();   // loads table from database Ticket
        update_AgentComboBox();
        update_AirlineComboBox();
        update_SaleComboBox();
        saleComboBox_to_TextField();
        saleTypeField.setEditable(false);

        // SEARCH button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String reportID = searchTextField.getText();

                    pst = main.con.prepareStatement("select reportID, timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency from Air_Ticket_Sales_Report where reportID = ?");
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

                        timeGeneratedField.setText(timeGenerated);
                        agentIDComboBox.setSelectedItem(agentID);
                        airlineIDComboBox.setSelectedItem(airlineID);
                        saleIDComboBox.setSelectedItem(saleID);
                        saleTypeField.setText(saleType);
                        PtypeComboBox.setSelectedItem(paymentType);
                        paymentCurrencyTextField.setText(paymentCurrency);

                    } else {
                        timeGeneratedField.setText("");
                        agentIDComboBox.setSelectedItem("");
                        airlineIDComboBox.setSelectedItem("");
                        saleIDComboBox.setSelectedItem("");
                        saleTypeField.setText("");
                        PtypeComboBox.setSelectedIndex(0);
                        paymentCurrencyTextField.setText("");
                        JOptionPane.showMessageDialog(mainPanel, "Invalid Ticket ID");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // DELETE button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reportID;
                reportID = searchTextField.getText();

                try {
                    pst = main.con.prepareStatement("delete from Air_Ticket_Sales_Report where reportID = ?");
                    pst.setString(1,reportID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Deleted");
                    load_table();
                    timeGeneratedField.setText("");
                    agentIDComboBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    saleTypeField.setText("");
                    PtypeComboBox.setSelectedIndex(0);
                    paymentCurrencyTextField.setText("");
                    searchTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        // SAVE button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency;

                timeGenerated = timeGeneratedField.getText();
                agentID = agentIDComboBox.getSelectedItem().toString();
                airlineID = airlineIDComboBox.getSelectedItem().toString();
                saleID = saleIDComboBox.getSelectedItem().toString();
                saleType = saleTypeField.getText();
                paymentType = PtypeComboBox.getSelectedItem().toString();
                paymentCurrency = paymentCurrencyTextField.getText();

                try {
                    pst = main.con.prepareStatement("insert into Air_Ticket_Sales_Report(timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency)values(?,?,?,?,?,?,?)");
                    pst.setString(1, timeGenerated);
                    pst.setString(2, agentID);
                    pst.setString(3, airlineID);
                    pst.setString(4, saleID);
                    pst.setString(5, saleType);
                    pst.setString(6, paymentType);
                    pst.setString(7, paymentCurrency);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Added");
                    load_table();
                    timeGeneratedField.setText("");
                    agentIDComboBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    saleTypeField.setText("");
                    PtypeComboBox.setSelectedIndex(0);
                    paymentCurrencyTextField.setText("");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reportID, timeGenerated, agentID, airlineID, saleID, saleType, paymentType, paymentCurrency;

                reportID = searchTextField.getText();
                timeGenerated = timeGeneratedField.getText();
                agentID = agentIDComboBox.getSelectedItem().toString();
                airlineID = airlineIDComboBox.getSelectedItem().toString();
                saleID = saleIDComboBox.getSelectedItem().toString();
                saleType = saleTypeField.getText();
                paymentType = PtypeComboBox.getSelectedItem().toString();
                paymentCurrency = paymentCurrencyTextField.getText();

                try {
                    pst = main.con.prepareStatement("update Air_Ticket_Sales_Report set timeGenerated = ?, agentID = ?, airlineID = ?, saleID = ?, saleType = ?, paymentType = ?, paymentCurrency = ? where reportID = ?");
                    pst.setString(1, timeGenerated);
                    pst.setString(2, agentID);
                    pst.setString(3, airlineID);
                    pst.setString(4, saleID);
                    pst.setString(5, saleType);
                    pst.setString(6, paymentType);
                    pst.setString(7, paymentCurrency);
                    pst.setString(8, reportID);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Record Updated");
                    load_table();
                    searchTextField.setText("");
                    timeGeneratedField.setText("");
                    agentIDComboBox.setSelectedIndex(0);
                    airlineIDComboBox.setSelectedIndex(0);
                    saleIDComboBox.setSelectedIndex(0);
                    saleTypeField.setText("");
                    PtypeComboBox.setSelectedIndex(0);
                    paymentCurrencyTextField.setText("");
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
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(mainPanel, "Exporting to PDF is Successful");
                    String path = "database/print/report.pdf";       // where the pdf will be located
                    String skip = "\n";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(path));

                    document.open();
                    main.connect();

                    pst = main.con.prepareStatement("select * from Air_Ticket_Sales_Report");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Paragraph paragraph = new Paragraph("reportID: " + rs.getString("reportID") + skip +
                                "timeGenerated: " + rs.getString("timeGenerated") + skip +
                                "agentID: " + rs.getString("agentID") + skip +
                                "airlineID: " + rs.getString("airlineID") + skip +
                                "saleID: " + rs.getString("saleID") + skip +
                                "saleType: " + rs.getString("saleType") + skip +
                                "paymentType: " + rs.getString("paymentType") + skip +
                                "paymentCurrency: " + rs.getString("paymentCurrency"));
                        document.add(paragraph);
                        document.add(new Paragraph(" " + skip));
                    }
                    document.close();

                } catch (DocumentException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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

    void update_AgentComboBox() {
        try {
            pst = main.con.prepareStatement("select * from Travel_Agent");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                agentIDComboBox.addItem(rs.getString("agentID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update_AirlineComboBox() {
        try {
            pst = main.con.prepareStatement("select * from Airline");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                airlineIDComboBox.addItem(rs.getString("airlineID"));
            }
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

    void saleComboBox_to_TextField() {
        String item = saleIDComboBox.getSelectedItem().toString();
        try {
            pst = main.con.prepareStatement("select * from Air_Ticket_Sale where saleID = ?");
            pst.setString(1,item);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                saleTypeField.setText(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}