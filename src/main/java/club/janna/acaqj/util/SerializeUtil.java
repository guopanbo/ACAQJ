package club.janna.acaqj.util;

import java.io.*;

/**
 * @author guopanbo
 * @Title: SerializeUtil
 * @Description: TODO
 * @date 2018/8/315:22
 */
public class SerializeUtil {
    /**
     * 序列化
     * @param obj
     * @return
     */
    public static byte[] serialize(Serializable obj) {
        if(obj == null)
            return null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null)
                try {
                    oos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if(baos != null)
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return null;
    }

    /**
     * 反序列化
     * @param arr
     * @return
     */
    public static Serializable deserialize(byte[] arr) {
        if(arr == null)
            return null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(arr);
            ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            if(obj != null)
                return (Serializable)obj;
        } catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
