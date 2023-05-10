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
    private static String tank1Type;
    private static String tank2Type;
    private static String tank1Name;
    private static String tank2Name;

    public SetupScreen() {
        setContentPane(mainPanel);
        setTitle("Game");
        setSize(1940, 1040);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        smallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank1Type = "Small";
            }
        });
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank1Type = "Medium";
            }
        });
        largeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank1Type = "Large";
            }
        });
        smallButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank2Type = "Small";
            }
        });
        mediumButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank2Type = "Medium";
            }
        });
        largeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank2Type = "Large";
            }
        });
        textField2.addKeyListener(new KeyAdapter() {
        });
    }

    public static String getTank1Type() {
        return tank1Type;
    }

    public static String getTank2Type() {
        return tank2Type;
    }

    public static String getTank1Name() {
        return tank1Name;
    }

    public static String getTank2Name() {
        return tank2Name;
    }
}
