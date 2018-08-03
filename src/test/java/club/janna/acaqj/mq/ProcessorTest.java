package club.janna.acaqj.mq;

/**
 * @author guopanbo
 * @Title: ProcessorTest
 * @Description: TODO
 * @date 2018/8/320:13
 */
public class ProcessorTest {
    public static void main(String[] args) {
        new Thread(new Processor()).start();
    }
}
