import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class Ghosts extends MoveableObjects {

    private char[] directions = { 'U', 'D', 'L', 'R', 'U', 'D', 'L', 'R' };
    Random random = new Random();

    public Ghosts(int x, int y, int width, int height, Image image) {

        super(x, y, width, image);
    }

    public void move(HashSet<GameObject> walls) {

        this.setX(getVelocityX() + this.getX());
        this.setY(getVelocityY() + this.getY());

        for (GameObject wall : walls) {

            if (isColliding(this, wall) || this.getX() <= 0 || this.getX() + this.getWidth() >= 608) {

                this.setX(this.getX() - getVelocityX());
                this.setY(this.getY() - getVelocityY());

                char newDirection = directions[random.nextInt(8)];
                this.updateDirection(newDirection, walls);
            }

        }

    }

}
