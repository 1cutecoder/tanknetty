package tank.cor;

import tank.Bullet;
import tank.GameObject;
import tank.Tank;
import tank.Wall;

/**
 * 类描述
 *
 * @author zcl
 * @Description 子弹和墙碰撞处理
 * @Date 2021/12/19 20:39
 */
public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            // 用一个rect来记录子弹的位置
            if (t.rect.intersects(w.rect)) {
                t.back();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            collide(o2, o1);
        }
        return false;
    }

}
