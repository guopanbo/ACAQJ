package club.janna.acaqj.mq;

import club.janna.acaqj.pojo.Area;
import club.janna.acaqj.pojo.ErrorLog;
import club.janna.acaqj.provider.ApplicationProvider;
import club.janna.acaqj.provider.RabbitMQProvider;
import club.janna.acaqj.provider.ConfigureProvider;
import club.janna.acaqj.service.AreaService;
import club.janna.acaqj.service.ErrorLogService;
import club.janna.acaqj.util.SerializeUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author guopanbo
 * @Title: Processor
 * @Description: TODO
 * @date 2018/8/319:59
 */
@Slf4j
public class Processor implements Runnable, Consumer {
    private Connection connection;
    private Channel channel;

    private AreaService areaService = ApplicationProvider.getInstance().getBean(AreaService.class);
    private ErrorLogService errorLogService = ApplicationProvider.getInstance().getBean(ErrorLogService.class);

    public Processor() {
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
            log.debug("RealDataProcessor init complete!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            channel.basicConsume(ConfigureProvider.getConfigure().getAreaQueueName(), true,this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void handleConsumeOk(String s) {

    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        Serializable serlz = SerializeUtil.deserialize(bytes);
        log.info("Delivery data:" + serlz);
        if(serlz == null)
            throw new RuntimeException("Data is null");
        //存储
        if(serlz instanceof Area) {
            Area area = (Area) serlz;
            log.debug("save area[code:{}, name:{}]", area.getCode(), area.getName());
            areaService.insert(area);
        } else if(serlz instanceof ErrorLog) {
            ErrorLog errorLog = (ErrorLog) serlz;
            log.debug("save error log [{}, {}]", errorLog.getMsg(), errorLog.getNote());
            errorLogService.insert(errorLog);
        }
    }
}
