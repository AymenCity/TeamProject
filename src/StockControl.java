import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockControl extends JFrame {
    private JPanel mainPanel;
    private JLabel stockControlLabel;
    private JComboBox blankComboBox;
    private JButton addBlankButton;
    private JButton removeBlankButton;
    private JButton applyButton;
    private JButton cancelButton;
    private JTextField blankIdTextField;
    private JTextField numberTextField;
    private JTextField priceTextField;
    private JTextField dateTextField;
    private JTextField timeTextField;
    private JTextField statusTextField;
    private JComboBox typeComboBox;
    private JLabel infoLabel;
    private JLabel blankIdLabel;
    private JLabel typeLabel;
    private JLabel numberLabel;
    private JLabel priceLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JLabel statusLabel;

    public StockControl() {
        setContentPane(mainPanel);
        setTitle("ATS System");          // name of software
        setSize(600, 400);   // window resolution
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage = new WelcomePage();
            }
        });
    }


}
