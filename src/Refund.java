import javax.swing.*;

public class Refund extends JFrame {
    private JPanel mainPanel;
    private JLabel RefundText;
    private JLabel chooseAUserLabel;
    private JList CustList;
    private JTable refundTable;
    private JLabel CopyrightLabel;

    public Refund() {
        setContentPane(mainPanel);
        setTitle("ATS System");        // name of software
        setSize(600, 400);   // window resolution
        setVisible(true);
    }
}

