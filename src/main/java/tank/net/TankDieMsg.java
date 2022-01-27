package tank.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import lombok.Data;
import tank.Bullet;
import tank.GameModel;
import tank.Tank;
import tank.TankFrame;

/**
 * @author: zcl
 * @date: 2022/1/27 14:03
 */
@Data
public class TankDieMsg extends Msg {
    /**
     * who killed me
     */
    public UUID bulletId;
    UUID id;

    public TankDieMsg(UUID playerId, UUID id) {
        this.bulletId = playerId;
        this.id = id;
    }

    public TankDieMsg() {
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(bulletId.getMostSignificantBits());
            dos.writeLong(bulletId.getLeastSignificantBits());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.bulletId = new UUID(dis.readLong(), dis.readLong());
            this.id = new UUID(dis.readLong(), dis.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void handle() {
        System.out.println("we got a tank die:" + id);
        System.out.println("and my tank is:" + GameModel.getInstance().getTankMap().get(id));
        Tank tt = GameModel.getInstance().findTankByUUID(id);
        System.out.println("i found a tank with this id:" + tt);

        Bullet b = GameModel.getInstance().findBulletByUUID(bulletId);
        if (b != null) {
            b.die();
        }

        if (this.id.equals(GameModel.getInstance().getTankMap().get(id))) {
            GameModel.getInstance().getTankMap().get(id).die();
        } else {

            Tank t = GameModel.getInstance().findTankByUUID(id);
            if (t != null) {
                t.die();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankDie;
    }

}
