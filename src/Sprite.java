import java.awt.Image;
import java.util.HashSet;

public class Sprite extends MoveableObjects {

    public Sprite(int x, int y, int size, Image image) {

        super(x, y, size, image);

    }

    public void move(HashSet<GameObject> walls) {

        this.setX(getVelocityX() + this.getX());
        this.setY(getVelocityY() + this.getY());

        for (GameObject wall : walls) {

            if (isColliding(this, wall)) {

                this.setX(this.getX() - getVelocityX());
                this.setY(this.getY() - getVelocityY());

            }

        }

    }
}
