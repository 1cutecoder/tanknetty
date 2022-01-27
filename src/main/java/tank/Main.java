package tank;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zcl
 * @date 2021/12/13 14:25
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        while (true) {
            try {TimeUnit.MILLISECONDS.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
            tf.repaint();
        }
    }

}
