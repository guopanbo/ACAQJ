package club.janna.acaqj.collect;

import club.janna.acaqj.pojo.ErrorLog;
import club.janna.acaqj.provider.ApplicationProvider;
import club.janna.acaqj.provider.ConfigureProvider;
import club.janna.acaqj.service.ErrorLogService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 错误处理器
 * @author guopanbo
 * @Title: ErrorHandler
 * @Description: TODO
 * @date 2018/8/411:52
 */
@Slf4j
public class ErrorHandler implements Runnable {

    private ErrorLogService errorLogService = ApplicationProvider.getInstance().getBean(ErrorLogService.class);
    private Integer total = ConfigureProvider.getConfigure().getErrorHandleTotalOfOnce();
    private Long waitTime = ConfigureProvider.getConfigure().getErrorHandleHandleWaitTimeForIdle();

    private void handle() {
        while(true) {
            List<ErrorLog> errorLogs = errorLogService.getByTotal(total);
            if(errorLogs != null) {
                for(ErrorLog errorLog : errorLogs)
                    if(errorLog != null)
                        Executor.getInstance().execute(new Collector(errorLog.getNote(), errorLog.getLevel(), errorLog.getPcode()));
            } else {
                log.info("There is no error log at present, waiting [time: {}] ...", waitTime);
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        handle();
    }
}
