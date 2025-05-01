import java.awt.Image;
import java.util.HashSet;

public abstract class MoveableObjects extends GameObject {

    private char direction;

    private int tileSize;
    private int velocityX;
    private int velocityY;

    public MoveableObjects() {

    }

    public MoveableObjects(int x, int y, int size, Image image) {

        super(x, y, size, size, image);

        this.velocityX = 0;
        this.velocityY = 0;
        this.direction = 'R';

        this.tileSize = size;
    }

    public void updateDirection(char direction, HashSet<GameObject> walls) {

        char prevDirection = this.direction;
        this.direction = direction;
        updateVelocity();

        this.setX(velocityX + this.getX());
        this.setY(velocityY + this.getY());

        for (GameObject wall : walls) {

            if (isColliding(this, wall)) {

                this.setX(this.getX() - velocityX);
                this.setY(this.getY() - velocityY);

                this.direction = prevDirection;
                updateVelocity();
            }
        }
    }

    public void updateVelocity() {

        if (this.direction == 'U') {

            velocityY = -tileSize / 4;
            velocityX = 0;

        } else if (this.direction == 'D') {

            velocityY = tileSize / 4;
            velocityX = 0;

        } else if (this.direction == 'L') {

            velocityY = 0;
            velocityX = -tileSize / 4;

        } else if (this.direction == 'R') {

            velocityY = 0;
            velocityX = tileSize / 4;
        }
    }

    public abstract void move(HashSet<GameObject> walls);

    public static boolean isColliding(GameObject a, GameObject b) {
        return a.getBounds().intersects(b.getBounds());
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int gettileSize() {
        return tileSize;
    }

    public char getDirection() {

        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}
