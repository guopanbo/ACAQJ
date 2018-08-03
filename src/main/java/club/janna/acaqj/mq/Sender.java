package club.janna.acaqj.mq;

import club.janna.acaqj.provider.RabbitMQProvider;
import club.janna.acaqj.provider.ConfigureProvider;
import club.janna.acaqj.util.SerializeUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author guopanbo
 * @Title: Sender
 * @Description: TODO
 * @date 2018/8/315:06
 */
@Slf4j
public class Sender {
    private Connection connection;
    private Channel channel;

    private Sender() {
        try {
            connection = RabbitMQProvider.getInstance().getConnection();
            if(connection == null) {
                log.error("Can't get rabbitmq connection!");
                throw new RuntimeException("Can't get rabbitmq connection!");
            }
            channel = connection.createChannel();
            if(channel == null) {
                log.error("Can't get rabbitmq channel!");
                throw new RuntimeException("Can't get rabbitmq channel!");
            }
            channel.queueDeclare(ConfigureProvider.getConfigure().getAreaQueueName(), true, false, false, null);
            log.info("RealDataMQSender init complete!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static class SenderHolder {
        private static final Sender instance = new Sender();
    }

    public static Sender getInstance() {
        return SenderHolder.instance;
    }

    public void sendMessage(Serializable serlz) {
        try {
            channel.basicPublish("",ConfigureProvider.getConfigure().getAreaQueueName(), null, SerializeUtil.serialize(serlz));
            log.info("Publish Data:" + serlz);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
