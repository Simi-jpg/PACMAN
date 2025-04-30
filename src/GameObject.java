import java.awt.*;

public abstract class GameObject {

    int x;
    int y;
    int width;
    int height;
    Image image;

    public GameObject() {

        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.image = null;
    }

    public GameObject(int x, int y, int width, int height, Image image) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;

    }

    public void draw(Graphics g) {

        if (image != null)
            g.drawImage(image, x, y, width, height, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
