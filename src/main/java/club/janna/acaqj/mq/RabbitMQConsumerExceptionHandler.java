package club.janna.acaqj.mq;

import com.rabbitmq.client.*;

/**
 * @author guopanbo
 * @Title: RabbitMQConsumerExceptionHandler
 * @Description: TODO
 * @date 2018/8/315:12
 */
public class RabbitMQConsumerExceptionHandler implements ExceptionHandler {


    public void handleUnexpectedConnectionDriverException(Connection connection, Throwable throwable) {

    }

    public void handleReturnListenerException(Channel channel, Throwable throwable) {

    }

    public void handleConfirmListenerException(Channel channel, Throwable throwable) {

    }

    public void handleBlockedListenerException(Connection connection, Throwable throwable) {

    }

    public void handleConsumerException(Channel channel, Throwable throwable, Consumer consumer, String s, String s1) {

    }

    public void handleConnectionRecoveryException(Connection connection, Throwable throwable) {

    }

    public void handleChannelRecoveryException(Channel channel, Throwable throwable) {

    }

    public void handleTopologyRecoveryException(Connection connection, Channel channel, TopologyRecoveryException e) {

    }
}