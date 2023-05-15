import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class SetupScreen extends JFrame{
    private JTextField textField1;
    private JLabel playerOneName;
    private JTextField textField2;
    private JLabel playerTwoName;
    private JButton largeButton;
    private JButton smallButton;
    private JButton mediumButton;
    private JButton smallButton1;
    private JButton mediumButton1;
    private JButton largeButton1;
    private JPanel mainPanel;
    private JButton startButton;

    private Display display;
    private static String tank1Type;
    private static String tank2Type;
    private static String tank1Name;
    private static String tank2Name;

    public SetupScreen() {
        setContentPane(mainPanel);
        setTitle("Game");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        smallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank1Type = "SPA";
            }
        });
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank1Type = "Light";
            }
        });
        largeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank1Type = "MBT";
            }
        });
        smallButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank2Type = "SPA";
            }
        });
        mediumButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank2Type = "Light";
            }
        });
        largeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank2Type = "MBT";
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank1Name = textField1.getText();
                tank2Name = textField2.getText();
                if (tank1Name != null && tank2Name != null && tank1Type != null && tank2Type != null && !tank1Name.equals(tank2Name))
                    display = new Display(tank1Name, tank1Type, tank2Name, tank2Type);
                dispose();
            }
        });
    }
}
