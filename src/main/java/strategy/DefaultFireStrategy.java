package strategy;

import tank.Bullet;
import tank.GameModel;
import tank.Tank;
import tank.decorator.GODecorator;
import tank.decorator.RetDecorator;
import tank.decorator.TailDecorator;

import java.io.Serializable;

/**
 * 类描述
 *
 * @author zcl
 * @Description 默认开火策略
 * @Date 2021/12/18 12:38
 */
public class DefaultFireStrategy implements FireStrategy, Serializable {
    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        GameModel.getInstance().add(new RetDecorator(new TailDecorator( new Bullet(bx, by, t.dir, t.group))));
    }
}
