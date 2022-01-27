package tank.net;

/**
 * 消息类型枚举
 * @author zcl
 */
public enum MsgType {
	//坦克加入
	TankJoin,
	//坦克方向更改
	TankDirChanged,
	//坦克停止
	TankStop,
	//坦克移动
	TankStartMoving,
	//新增子弹
	BulletNew,
	//坦克死亡
	TankDie
}

