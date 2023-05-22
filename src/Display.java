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
    private Tank playerOne;
    private Tank playerTwo;
    private boolean isFirstPlayerTurn;

    public Display(String name1, String type1, String name2, String type2) {
        isFirstPlayerTurn = true;
        playerOne = new Tank(name1, type1, 0, 0);
        playerTwo = new Tank(name2, type2, 100, 0);
        Player.setText(playerOne.getName() + "'s turn.");
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
                    if (turnOrder[0].getName().substring(turnOrder[0].getName().length()-1).equalsIgnoreCase("s")) {
                        Player.setText(turnOrder[0].getName() + "'s turn.");
                    } else {
                        Player.setText(turnOrder[0].getName() + "' turn.");
                    }
                    Projectile proj = new Projectile(Integer.parseInt(currentPow.getText()), playerOne.getDamage(), Double.parseDouble(currentAng.getText()), playerOne.getX(), playerOne.getY());
                    isFirstPlayerTurn = false;
                } else {
                    Player.setText(turnOrder[1].getName());
                    Projectile proj = new Projectile(Integer.parseInt(currentPow.getText()), playerTwo.getDamage(), Double.parseDouble(currentAng.getText()), playerTwo.getX(), playerTwo.getY());
                    isFirstPlayerTurn = true;
                }

            }
        });
    }

}
