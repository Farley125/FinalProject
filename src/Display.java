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
    private KeyHandler keyH = new KeyHandler();
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
        setContentPane(mainPanel);
        setTitle("Game");
        setSize(1940, 1040);
        setResizable(false);
        setLocationRelativeTo(null);
        mainPanel.setDoubleBuffered(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        startGameThread();
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
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        int capSlider = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                if (capSlider == 2) {
                    update();
                    capSlider = 0;
                } else capSlider++;
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (keyH.isUpPressed()) {
            System.out.println("up");
            Power.setValue(Power.getValue() + 1);
        } else if (keyH.isDownPressed()) {
            System.out.println("down");
            Power.setValue(Power.getValue() - 1);
        } else if (keyH.isLeftPressed()) {
            System.out.println("left");
            Angle.setValue(Angle.getValue() - 1);
        } else if (keyH.isRightPressed()) {
            System.out.println("right");
            Angle.setValue(Angle.getValue() + 1);
        }
    }
}
