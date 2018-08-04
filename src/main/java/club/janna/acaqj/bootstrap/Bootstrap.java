package club.janna.acaqj.bootstrap;

import club.janna.acaqj.collect.Collector;
import club.janna.acaqj.collect.ErrorHandler;
import club.janna.acaqj.collect.Executor;
import club.janna.acaqj.config.Configure;
import club.janna.acaqj.mq.Processor;
import club.janna.acaqj.provider.ConfigureProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Auther: guopanbo
 * @Date: 2018/8/2 21:03
 * @Description:
 */
@Slf4j
public class Bootstrap {
    public static void main(String[] args) {
        Configure configure = ConfigureProvider.getConfigure();
        Executor.getInstance().execute(new Collector(configure.getRoot(), 1, null));
//        Executor.getInstance().shutdown();
        ThreadPoolExecutor executor = Executor.getInstance().getThreadPoolExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //运行rabbit mq 处理机
        for(int i = 0; i < configure.getProcessorThreadNum(); i ++)
            executorService.execute(new Processor());
        //运行错误处理器
        for(int i = 0; i < configure.getErrorHandlerThreadNum(); i ++)
            executorService.execute(new ErrorHandler());
        while(true) {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("queue size:{}, active count:{}, completed count:{}, task count:{}", executor.getQueue().size(), executor.getActiveCount(), executor.getCompletedTaskCount(), executor.getTaskCount());
        }
    }
}
