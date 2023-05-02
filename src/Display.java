import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame {
    private JSlider Power;
    private JSlider Angle;
    private JLabel Player;
    private JButton fireButton;
    private JPanel mainPanel;
    private JLabel powLabel;
    private JLabel angSlider;
    private JLabel currentPow;
    private JLabel currentAng;
    private Tank[] turnOrder;
    private boolean isFirstPlayerTurn;

    public Display() {
        isFirstPlayerTurn = true;
        setContentPane(mainPanel);
        setTitle("Game");
        setSize(1940, 1040);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Power.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentPow.setText(Integer.toString(Power.getValue()));
            }
        });
        Angle.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentAng.setText(Integer.toString(Angle.getValue()));
            }
        });
        fireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isFirstPlayerTurn) {
                    Player.setText(turnOrder[0].getName());
                    isFirstPlayerTurn = false;
                } else {
                    Player.setText(turnOrder[1].getName());
                    isFirstPlayerTurn = true;
                }
            }
        });
    }

}
