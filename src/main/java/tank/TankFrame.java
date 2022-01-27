package tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcl
 * @date 2021/12/13 14:13
 */
public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 1280, GAME_HEIGHT = 720;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War ");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyKeyListener());
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        GameModel.getInstance().paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量：" + gm.bullets.size(), 10, 60);
//        g.drawString("敌人的数量：" + gm.tanks.size(), 10, 80);
//        g.drawString("爆炸的数量：" + gm.explodes.size(), 10, 100);
        g.setColor(c);
        GameModel.getInstance().myTank.paint(g);

    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bu = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_S:
                    GameModel.getInstance().save();
                    break;
                case KeyEvent.VK_L:
                    GameModel.getInstance().load();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    GameModel.getInstance().myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            Tank myTank = GameModel.getInstance().getMainTank();
            if (!bL && !bu && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
            }
            if (bL) {
                myTank.setDir(Dir.LEFT);
            }
            if (bu) {
                myTank.setDir(Dir.UP);
            }
            if (bR) {
                myTank.setDir(Dir.RIGHT);
            }
            if (bD) {
                myTank.setDir(Dir.DOWN);
            }
        }
    }
}
