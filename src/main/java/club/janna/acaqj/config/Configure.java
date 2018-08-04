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

    /**
     * 根节点
     */
    private String root;

    /**
     * rabbit mq
     */
    private RabbitMQ rabbitMQ;

    /**
     * 队列名称
     */
    private String areaQueueName;
    private String errorLogQueueName;

    /**
     * 服务器不可用时重试间隔时间 （毫秒）
     */
    private Long tryAgainIntervalTime;
    /**
     * 服务器不可用时重试次数
     */
    private Integer tryAgainNum;
}
