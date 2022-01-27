package tank;


import java.awt.*;

/**
 * @author zcl
 * @date 2021/12/14 14:02
 */
public class Explode extends GameObject {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int step = 0;

    public Explode(int x, int y) {
        super.x = x;
        super.y = y;
        new Thread(() -> {
            Audio audio = new Audio("audio/explode.wav");
            audio.play();
            audio.close();
        }, "a").start();

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            step = 0;
            GameModel.getInstance().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }


}
