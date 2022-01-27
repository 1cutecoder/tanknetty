package tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * 类描述
 *
 * @author zcl
 * @Description 游戏物体
 * @Date 2021/12/19 13:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class GameObject implements Serializable {
    public int x, y;
    public UUID id;

    public abstract void paint(Graphics g);

    public abstract int getWidth();

    public abstract int getHeight();
}
