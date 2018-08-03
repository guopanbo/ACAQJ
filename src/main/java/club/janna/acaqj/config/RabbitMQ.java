package club.janna.acaqj.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guopanbo
 * @Title: RabbitMQ
 * @Description: TODO
 * @date 2018/8/315:34
 */
@Getter
@Setter
public class RabbitMQ {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String vhost;
}
