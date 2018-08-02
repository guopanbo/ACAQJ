package club.janna.acaqj.bootstrap;

import club.janna.acaqj.collect.Collector;
import club.janna.acaqj.collect.Executor;
import club.janna.acaqj.model.Configure;
import org.yaml.snakeyaml.Yaml;


/**
 * @Auther: guopanbo
 * @Date: 2018/8/2 21:03
 * @Description:
 */
public class Bootstrap {
    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        Configure configure = yaml.loadAs(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.yml"), Configure.class);
        if(configure == null)
            throw new RuntimeException("configure file load failed");
        Executor.getInstance().execute(new Collector(configure.getRoot(), 1, null));
//        Executor.getInstance().shutdown();
    }
}
