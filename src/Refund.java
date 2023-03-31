import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Refund extends JFrame {
    private JPanel mainPanel;
    private JList CustomerList;
    private JTable refundTable;

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
    }
    private void createTable(){
        refundTable.setModel(new DefaultTableModel(
                null,
                new String[]{"ID","Name","Payment"}
        ));
    }
}

