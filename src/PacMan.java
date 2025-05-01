import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class PacMan extends JPanel implements ActionListener, KeyListener {

    // can get ab interface to initialize a random tilemap
    private String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    private char[] directions = { 'U', 'D', 'L', 'R', 'U', 'D', 'L', 'R' };
    Random random = new Random();

    Timer gameLoop;

    // wall
    private Image wallImg;

    // ghosts
    private Image blueGhostImg;
    private Image orangeGhostImg;
    private Image pinkGhostImg;
    private Image redGhostImg;

    // pacman directions
    private Image pacmanUpImg;
    private Image pacmanDownImg;
    private Image pacmanLeftImg;
    private Image pacmanRightImg;

    public HashSet<GameObject> walls;
    public HashSet<GameObject> ghosts;
    public HashSet<GameObject> foods;
    public GameObject pacman;

    public PacMan(int width, int height, int tileSize) {

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        // initialize images
        wallImg = new ImageIcon(getClass().getResource("./wall.png")).getImage(); // the file path of where the image is

        blueGhostImg = new ImageIcon(getClass().getResource("./blueGhost.png")).getImage();
        orangeGhostImg = new ImageIcon(getClass().getResource("./orangeGhost.png")).getImage();
        pinkGhostImg = new ImageIcon(getClass().getResource("./pinkGhost.png")).getImage();
        redGhostImg = new ImageIcon(getClass().getResource("./redGhost.png")).getImage();

        pacmanUpImg = new ImageIcon(getClass().getResource("./pacmanUp.png")).getImage();
        pacmanDownImg = new ImageIcon(getClass().getResource("./pacmanDown.png")).getImage();
        pacmanLeftImg = new ImageIcon(getClass().getResource("./pacmanLeft.png")).getImage();
        pacmanRightImg = new ImageIcon(getClass().getResource("./pacmanRight.png")).getImage();

        loadMap(height / tileSize, width / tileSize, tileSize);

        for (GameObject ghost : ghosts) {

            char newDirection = directions[random.nextInt(8)];

            if (ghost instanceof Ghosts) {
                Ghosts g = (Ghosts) ghost;

                g.updateDirection(newDirection, walls);
            }
        }

        gameLoop = new Timer(50, this); // every 20 frms ps, we repaint
        gameLoop.start();
    }

    private void loadMap(int rowCount, int colCount, int tileSize) {

        walls = new HashSet<>();
        ghosts = new HashSet<>();
        foods = new HashSet<>();

        for (int r = 0; r < rowCount; r++) {

            for (int c = 0; c < colCount; c++) {

                String row = tileMap[r];
                char tileMapChar = row.charAt(c);

                int x = c * tileSize; // how many tiles from the left
                int y = r * tileSize; // how many tiles from the top

                if (tileMapChar == 'X') {

                    GameObject wall = new Wall(x, y, tileSize, tileSize, wallImg);
                    walls.add(wall); // add to the hashset

                } else if (tileMapChar == 'b') { // blue ghost

                    GameObject ghost = new Ghosts(x, y, tileSize, tileSize, blueGhostImg);
                    ghosts.add(ghost);

                } else if (tileMapChar == 'o') { // orange ghost

                    GameObject ghost = new Ghosts(x, y, tileSize, tileSize, orangeGhostImg);
                    ghosts.add(ghost);

                } else if (tileMapChar == 'p') { // pink ghost

                    GameObject ghost = new Ghosts(x, y, tileSize, tileSize, pinkGhostImg);
                    ghosts.add(ghost);

                } else if (tileMapChar == 'r') { // red ghost

                    GameObject ghost = new Ghosts(x, y, tileSize, tileSize, redGhostImg);
                    ghosts.add(ghost);

                } else if (tileMapChar == 'P') { // pacman

                    pacman = new Sprite(x, y, tileSize, pacmanRightImg);

                } else if (tileMapChar == ' ') {

                    GameObject food = new Food(x + 14, y + 14, 4);
                    foods.add(food);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {

        for (GameObject wall : walls) {

            wall.draw(g);
        }

        for (GameObject ghost : ghosts) {

            ghost.draw(g);
        }

        g.setColor(Color.GREEN);
        for (GameObject food : foods) {

            food.draw(g);
        }

        pacman.draw(g);

    }

    // ActionListener starts
    public void actionPerformed(ActionEvent e) { // game loop

        if (pacman instanceof Sprite) { // if pacman is a sprite, move it

            Sprite player = (Sprite) pacman;
            player.move(walls);

        }

        for (GameObject ghost : ghosts) {

            if (ghost instanceof Ghosts) {
                Ghosts g = (Ghosts) ghost;
                g.move(walls); // Add this to animate ghost movement
            }
        }

        repaint();
    }

    // ActionListener ends

    // KeyListenerstarts
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) { // only need this

        // System.out.println("KeyEvent: " + e.getKeyCode());
        if (pacman instanceof Sprite) {

            Sprite player = (Sprite) pacman;

            if (e.getKeyCode() == KeyEvent.VK_UP) {

                player.updateDirection('U', walls);

            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                player.updateDirection('D', walls);

            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                player.updateDirection('L', walls);

            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                player.updateDirection('R', walls);
            }

            if (player.getDirection() == 'U') {

                player.setImage(pacmanUpImg);

            } else if (player.getDirection() == 'D') {

                player.setImage(pacmanDownImg);

            } else if (player.getDirection() == 'L') {

                player.setImage(pacmanLeftImg);

            } else if (player.getDirection() == 'R') {

                player.setImage(pacmanRightImg);

            }
        }

    }

    // KeyListenerends
}
