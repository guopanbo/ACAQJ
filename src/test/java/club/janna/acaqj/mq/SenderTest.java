package club.janna.acaqj.mq;

import club.janna.acaqj.pojo.Area;
import club.janna.acaqj.pojo.ErrorLog;

/**
 * @author guopanbo
 * @Title: SenderTest
 * @Description: TODO
 * @date 2018/8/320:13
 */
public class SenderTest {
    public static void main(String[] args) {
        Area area = new Area();
        area.setName("成都");
        Sender.getInstance().sendMessage(area);
        ErrorLog errorLog = new ErrorLog();
        errorLog.setMsg("abc");
        Sender.getInstance().sendMessage(errorLog);
    }
}
