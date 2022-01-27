package strategy;

import tank.Tank;

/**
 * @author zcl
 */
public interface FireStrategy {
    /**
     * 坦克开火的策略
     */
    public void fire(Tank t);
}
