package tank;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.UUID;

/**
 * @author zcl
 * @date 2021/12/14 14:37
 */
@Data
@NoArgsConstructor
public class Bullet extends GameObject {
    private final int SPEED = 10;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    public Rectangle rect = new Rectangle();
    public Dir dir;
    private boolean living = true;
    public Group group = Group.BAD;
    public UUID playerId;

    public Bullet(int x, int y, Dir dir, Group group) {
        super.x = x;
        super.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Bullet(UUID playerId, int x, int y, Dir dir, Group group) {
        super.x = x;
        super.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            GameModel.getInstance().remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
        //update rect
        rect.x = super.x;
        rect.y = super.y;
    }


    public void die() {
        this.living = false;
    }
}
