package club.janna.acaqj.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guopanbo
 * @Title: Configure
 * @Description: TODO
 * @date 2018/8/223:09
 */
@Getter
@Setter
public class Configure {
    private String root;
    private RabbitMQ rabbitMQ;
    private String areaQueueName;
    private String errorLogQueueName;
}
