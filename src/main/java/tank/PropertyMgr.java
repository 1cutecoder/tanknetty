package tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author zcl
 */
public class PropertyMgr {
    static Properties props = null;

    public PropertyMgr() {
        if (props == null) {
            synchronized (PropertyMgr.class) {
                if (props == null) {
                    synchronized (PropertyMgr.class) {
                    }
                }
            }
        }
    }

    static {
        props = new Properties();
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getString(String key) {
        if (props == null) {
            return null;
        }
        return (String) props.get(key);
    }

    public static String[] getStrings(String key) {
        if (props == null) {
            return null;
        }
        String strings = (String) props.get(key);
        return strings.split(",");
    }

    public static int getInt(String key) {
        if (props == null) {
            throw new NullPointerException();
        }
        return Integer.parseInt((String) props.get(key));
    }
}
