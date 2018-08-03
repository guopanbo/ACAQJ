package club.janna.acaqj.provider;

import club.janna.acaqj.config.RabbitMQ;
import club.janna.acaqj.mq.RabbitMQConsumerExceptionHandler;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author guopanbo
 * @Title: RabbitMQProvider
 * @Description: TODO
 * @date 2018/8/315:08
 */
public class RabbitMQProvider {
    private Channel channel;
    private Connection connection;
    private static final ConnectionFactory factory;


    static {
        //Create a connection factory
        factory = new ConnectionFactory();
        RabbitMQ rabbitMQ = ConfigureProvider.getConfigure().getRabbitMQ();
        try {
            factory.setHost(rabbitMQ.getHost());
            factory.setPort(rabbitMQ.getPort());
            factory.setUsername(rabbitMQ.getUsername());
            factory.setPassword(rabbitMQ.getPassword());
            factory.setVirtualHost(rabbitMQ.getVhost());
        } catch(Exception e) {
            throw new RuntimeException("RabbitMQProvider init faild!");
        }
        factory.setExceptionHandler(new RabbitMQConsumerExceptionHandler());
    }


    private static class SimpleRabbitMQFactory {
        private static final RabbitMQProvider instance = new RabbitMQProvider();
    }

    private RabbitMQProvider() {
    }

    public static RabbitMQProvider getInstance() {
        return SimpleRabbitMQFactory.instance;
    }

    public Connection getConnection() {
        try {
            return factory.newConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

//	public Channel createChannel() {
//		Connection conn = getConnection();
//		try {
//			return conn.createChannel();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

    private RabbitMQProvider(String name) {
        try {
            //getting a connection
            connection = factory.newConnection();
            //creating a channel
            channel = connection.createChannel();
            //declaring a queue for this channel. If queue does not exist,
            //it will be created on the server.
            channel.queueDeclare(name, false, false, false, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void close(Connection conn) {
        if(conn != null)
            try {
                conn.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    public void close(Channel channel) {
        if(channel != null)
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
