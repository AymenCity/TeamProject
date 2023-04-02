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

    public Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(600, 400);   // window resolution
        setVisible(true);

        createTable();

       /* String[] Columns = {"ID","Name","Payment"};
        String[][] Data = {
                {"123","John","Cash"},
                {"321","Jane","Card"},
                {"312","Joe","Card"}
        };
        JTable refundTable = new JTable(Data,Columns);
        this.add(refundTable);*/
        returnButton.addActionListener(new ActionListener() {
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
                new String[]{"ID","Name","Payment"}
        ));
    }
}

