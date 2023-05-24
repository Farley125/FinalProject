import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements Runnable{
    private JSlider Power;
    private JSlider Angle;
    private JLabel Player;
    private JButton fireButton;
    private JPanel mainPanel;
    private JLabel powLabel;
    private JLabel angSlider;
    private JLabel currentPow;
    private JLabel currentAng;
    private Tank[] turnOrder = new Tank[2];
    private Tank playerOne;
    private Tank playerTwo;
    private boolean isFirstPlayerTurn;
    Thread gameThread;
    final int FPS = 60;

    public Display(String name1, String type1, String name2, String type2) {
        isFirstPlayerTurn = true;
        playerOne = new Tank(type1, name1, 0, 0);
        playerTwo = new Tank(type2, name2, 100, 0);
        turnOrder[0] = playerOne;
        turnOrder[1] = playerTwo;
        Power.setFocusable(false);
        Angle.setFocusable(false);
        Player.setText(playerOne.getName() + "'s turn.");
        KeyHandler keyH = new KeyHandler();
        Thread gameThread;
        setContentPane(mainPanel);
        setTitle("Game");
        setSize(1940, 1040);
        setResizable(false);
        setLocationRelativeTo(null);
        mainPanel.setDoubleBuffered(true);
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
                        Player.setText(turnOrder[0].getName() + "'s turn.");
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

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
