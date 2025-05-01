import java.awt.*;

public abstract class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;

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
        else
            System.out.println("NULL");
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {

        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image img) {
        this.image = img;
    }
}
