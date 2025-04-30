import java.awt.Image;
import java.util.HashSet;

public class Sprite extends GameObject {

    char direction;

    int tileSize;
    int velocityX;
    int velocityY;

    public Sprite(int x, int y, int size, Image image) {

        super(x, y, size, size, image);

        this.velocityX = 0;
        this.velocityY = 0;
        this.direction = 'R';

        this.tileSize = size;
    }

    public void updateDirection(char direction) {

        this.direction = direction;
        updateVelocity();

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

    public void move(HashSet<GameObject> walls) {

        x += velocityX;
        y += velocityY;

        for (GameObject wall : walls) {

            if (isColliding(this, wall)) {

                x -= velocityX;
                y -= velocityY;
            }

        }

    }

    // public boolean isColliding(GameObject blockA, GameObject blockB) {

    // return blockA.x < blockB.x + blockB.width
    // && blockA.x + blockA.width > blockB.x
    // && blockA.y < blockB.y + blockB.height
    // && blockA.y + blockA.height > blockB.y;
    // }

    public static boolean isColliding(GameObject a, GameObject b) {
        return a.getBounds().intersects(b.getBounds());
    }
}
