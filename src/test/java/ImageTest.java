import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zcl
 * @date 2021/12/15 16:14
 */
public class ImageTest {
    @Test
    public void test() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/0.gif"));
        Assert.assertNotNull(bufferedImage);
    }
}

