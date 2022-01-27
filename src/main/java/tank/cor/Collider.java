package tank.cor;

import tank.GameObject;

/**
 * 类描述
 *
 * @author zcl
 * @Description 负责两个游戏物体之间的碰撞
 * @Date 2021/12/19 20:36
 */
public interface Collider {
    /**
     * 两游戏物体碰撞
     * @param o1
     * @param o2
     */
    boolean collide(GameObject o1,GameObject o2);
}
