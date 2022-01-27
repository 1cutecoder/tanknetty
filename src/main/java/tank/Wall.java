package tank;

import java.awt.*;

/**
 * @author zcl
 * @date 2021/12/20 10:02
 */
public class Wall extends GameObject {
    int w, h;
    public Rectangle rect;

    public Wall(int x, int y, int w, int h) {
        super.x = x;
        super.y = y;
        this.w = w;
        this.h = h;
        this.rect = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }
}
