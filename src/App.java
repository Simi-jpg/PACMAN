import javax.swing.JFrame; //for the window

public class App {
    public static void main(String[] args) throws Exception {

        int columns = 19;
        int rows = 21;
        int tileSize = 32;

        // sets the size of the board
        int boardWidth = columns * tileSize;
        int boardHeight = rows * tileSize;

        JFrame frame = new JFrame("PACMAN"); // window
        frame.setSize(boardWidth, boardHeight); // size of the window
        frame.setLocationRelativeTo(null); // appear at the center of the screen
        frame.setResizable(false); // player cannot resize the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when the player clicks on "X"

        PacMan pacMan = new PacMan(boardWidth, boardHeight, tileSize); // initialize the PacMan
        frame.add(pacMan); // add to the window
        frame.pack(); // get the full size of the jPanel within the window
        pacMan.requestFocus(); // make the PacMan focus on the window
        frame.setVisible(true); // make the frame visible to player

    }
}
