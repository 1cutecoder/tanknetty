package tank.decorator;

import tank.GameObject;

import java.awt.*;

/**
 * @author zcl
 * @date 2021/12/20 17:26
 */
public class TailDecorator extends GODecorator {

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(go.x, go.y, go.x + getWidth(), go.y + getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
