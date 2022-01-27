package tank.cor;

import tank.*;

/**
 * 类描述
 *
 * @author zcl
 * @Description 子弹和墙碰撞处理
 * @Date 2021/12/19 20:39
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            // 用一个rect来记录子弹的位置
            if (b.rect.intersects(w.rect)) {
                b.die();
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            collide(o2, o1);
        }
        return false;
    }

}
