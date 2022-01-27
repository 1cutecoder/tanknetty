package tank.decorator;

import tank.GameObject;

import java.awt.*;

/**
 * @author zcl
 * @date 2021/12/20 17:24
 */
public abstract class GODecorator extends GameObject {

    GameObject go;

    protected GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }


}
