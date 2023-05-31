import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements Runnable {
    private JSlider powerSlider;
    private JSlider angleSlider;
    private JLabel Player;
    private JButton fireButton;
    private JPanel mainPanel;
    private JLabel powLabel;
    private JLabel angLabel;
    private JLabel currentPow;
    private JLabel currentAng;
    private Tank[] turnOrder = new Tank[2];
    private Tank playerOne;
    private Tank playerTwo;
    private Image tankIMG1;
    private Image tankIMG2;
    private boolean isFirstPlayerTurn;
    private KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    final int FPS = 60;

    public Display(String name1, String type1, String name2, String type2) {
        setContentPane(mainPanel);
        mainPanel. add(new CustomPaintComponent());
        String imageURL1 = "src/green tank.png";
        String imageURL2 = "src/red tank.png";
        tankIMG1 = Toolkit.getDefaultToolkit().getImage(imageURL1);
        tankIMG2 = Toolkit.getDefaultToolkit().getImage(imageURL2);
        isFirstPlayerTurn = true;
        playerOne = new Tank(type1, name1, 130, 710);
        playerTwo = new Tank(type2, name2, 1770, 710);
        turnOrder[0] = playerOne;
        turnOrder[1] = playerTwo;
        powerSlider.setFocusable(false);
        angleSlider.setFocusable(false);
        if (playerOne.getName().substring(turnOrder[0].getName().length() - 1).equalsIgnoreCase("s")) {
            Player.setText(playerOne.getName() + "' turn.");
        } else {
            Player.setText(playerOne.getName() + "'s turn.");
        }
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
        powerSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentPow.setText(Integer.toString(powerSlider.getValue()));
            }
        });
        angleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentAng.setText(Integer.toString(angleSlider.getValue()));
            }
        });
        fireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isFirstPlayerTurn) {
                    if (turnOrder[0].getName().substring(turnOrder[0].getName().length() - 1).equalsIgnoreCase("s")) {
                        Player.setText(turnOrder[0].getName() + "' turn.");
                    } else {
                        Player.setText(turnOrder[0].getName() + "'s turn.");
                    }
                    playerOne.shoot(Integer.parseInt(currentPow.getText()), Integer.parseInt(currentAng.getText()));
                    isFirstPlayerTurn = false;
                } else {
                    Player.setText(turnOrder[1].getName());
                    playerTwo.shoot(Integer.parseInt(currentPow.getText()), Integer.parseInt(currentAng.getText()));
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
        double drawInterval = 1000000000 / FPS;
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
        mainPanel.repaint();
        if (keyH.isUpPressed()) {
            powerSlider.setValue(powerSlider.getValue() + 1);
        } else if (keyH.isDownPressed()) {
            powerSlider.setValue(powerSlider.getValue() - 1);
        } else if (keyH.isLeftPressed()) {
            angleSlider.setValue(angleSlider.getValue() - 1);
        } else if (keyH.isRightPressed()) {
            angleSlider.setValue(angleSlider.getValue() + 1);
        } else if (keyH.isaPressed()) {
            if (isFirstPlayerTurn) {
                if (playerOne.getX() > 0) {
                    playerOne.setX(playerOne.getX() - 5);
                } else {
                    if (playerTwo.getX() > 0) {
                        playerTwo.setX(playerTwo.getX() - 5);
                    }
                }
            }
            mainPanel.repaint();
        } else if (keyH.isdPressed()) {
            if (isFirstPlayerTurn) {
                if (playerOne.getX() < 1900) {
                    playerOne.setX(playerOne.getX() + 5);
                    } else {
                    if (playerTwo.getX() < 1900) {
                        playerTwo.setX(playerTwo.getX() + 5);
                    }
                }
            }
            mainPanel.repaint();
        }
    }

    public class CustomPaintComponent extends Component {
        public void paint(Graphics g) {
            System.out.println("Paint " + playerOne.getX() + " " + playerOne.getY());
            Graphics2D g2d = (Graphics2D)g;
            System.out.println(tankIMG1);
            g2d.drawImage(tankIMG1, playerOne.getX(), playerOne.getY(), null);
            System.out.println("Paint " + playerTwo.getX() + " " + playerTwo.getY());
            System.out.println(tankIMG2);
            g2d.drawImage(tankIMG2, playerTwo.getX(), playerTwo.getY(), null);
        }
    }
}