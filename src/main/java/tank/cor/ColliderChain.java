package tank.cor;

import tank.GameObject;
import tank.PropertyMgr;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 类描述
 *
 * @author zcl
 * @Description 碰撞责任链类
 * @Date 2021/12/19 21:18
 */
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public void add(Collider c) {
        colliders.add(c);
    }


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            boolean b = colliders.get(i).collide(o1, o2);
            if (b) {
                return true;
            }
        }
        return false;
    }
}
