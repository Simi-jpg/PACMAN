import java.awt.*;

public class Food extends StaticObjects {

    private int size;

    public Food(int x, int y, int size) {

        super(x, y, size, size, null);
        this.size = size;
    }

    public void draw(Graphics g) {

        g.setColor(Color.GREEN);
        g.fillRect(this.getX(), this.getY(), size, size);
    }
}
