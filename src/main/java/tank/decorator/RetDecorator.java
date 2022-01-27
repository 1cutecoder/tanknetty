package tank.decorator;

import tank.GameObject;

import java.awt.*;

/**
 * @author zcl
 * @date 2021/12/20 17:26
 */
public class RetDecorator extends GODecorator{

    public RetDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(go.x,go.y,getWidth(),getHeight());
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
