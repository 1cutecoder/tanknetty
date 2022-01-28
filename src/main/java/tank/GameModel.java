package tank;

import tank.cor.BulletTankCollider;
import tank.cor.Collider;
import tank.cor.ColliderChain;
import tank.cor.TankTankCollider;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * 类描述
 *
 * @author zcl
 * @Description model层
 * @Date 2021/12/19 12:44
 */
public class GameModel {

    private List<GameObject> objects = new ArrayList<>();
    private Map<UUID,Tank> tankMap= new ConcurrentHashMap<>();
    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);
    private ColliderChain chain = new ColliderChain();
    private volatile static GameModel INSTANCE;

    {
        init();
    }

    private GameModel() {

    }

    private void init() {
        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            addTank(new Tank(UUID.randomUUID(),UUID.randomUUID(),50 + i * 80, 200, Dir.DOWN, Group.BAD));
        }
        //创建责任链的对象
        String[] colliderStrs = PropertyMgr.getStrings("colliders");
        for (int i = 0; i <= colliderStrs.length - 1; i++) {
            try {
                chain.add((Collider) Class.forName(colliderStrs[i]).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    public static GameModel getInstance() {
        if (INSTANCE == null) {
            synchronized (GameModel.class) {
                if (INSTANCE == null) {
                    synchronized (GameModel.class) {
                        INSTANCE = new GameModel();
                    }
                }
            }
        }
        return INSTANCE;
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }
    public void addTank(Tank tank) {
        tankMap.put(tank.getId(),tank);
    }
    public void remove(GameObject go) {
        objects.remove(go);
    }
    public void removeTank(Tank tank) {
        tankMap.remove(tank.getId());
    }
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        /*g.drawString("子弹的数量：" + objects.size(), 10, 60);
        g.drawString("敌人的数量：" + objects.size(), 10, 80);
        g.drawString("爆炸的数量：" + objects.size(), 10, 100);*/
        g.setColor(c);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        //互相碰撞的逻辑
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);
            }
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
    public void save() {
        File f = new File("d:/tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(tankMap);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        File f = new File("d:/tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            tankMap = (ConcurrentHashMap<UUID,Tank>) ois.readObject();
            objects = (List<GameObject>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Tank findTankByUUID(UUID id) {

        return null;
    }

    public Bullet findBulletByUUID(UUID bulletId) {
        return null;
    }

    public <T> T findGameeObjectByUUID(UUID queryObject) {

        return null;
    }
}
