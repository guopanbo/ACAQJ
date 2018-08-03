package club.janna.acaqj.provider;

import club.janna.acaqj.config.Configure;
import org.yaml.snakeyaml.Yaml;

/**
 * @author guopanbo
 * @Title: ConfigureProvider
 * @Description: TODO
 * @date 2018/8/315:26
 */
public class ConfigureProvider {

    private static Configure configure;

    static {
        Yaml yaml = new Yaml();
        configure = yaml.loadAs(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.yml"), Configure.class);
        if(configure == null)
            throw new RuntimeException("configure file load failed");
    }

    public static Configure getConfigure() {
        return configure;
    }
}
