package tank.cor;

import tank.*;

/**
 * 类描述
 *
 * @author zcl
 * @Description 子弹和坦克碰撞处理
 * @Date 2021/12/19 20:39
 */
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            if (b.group == t.group) {
                return false;
            }
            // 用一个rect来记录子弹的位置
            if (b.rect.intersects(t.rect)) {
                t.die();
                b.die();
                int eX = t.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = t.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                GameModel.getInstance().add(new Explode(eX, eY));
            }
            return true;
        } else if (o2 instanceof Bullet && o1 instanceof Tank){
            collide(o2,o1);
        }
        return false;
    }
}
