package club.janna.acaqj.collect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author guopanbo
 * @Title: Executor
 * @Description: TODO
 * @date 2018/8/222:29
 */
public class Executor {
    private ExecutorService executorService;
    private static volatile Executor instance;

    private Executor() {
        //初始化线程池
        executorService = Executors.newCachedThreadPool();
    }

    public static Executor getInstance() {
        /**
         * double check + volatile 保证线程安全
         */
        if(instance == null)
            synchronized (Executor.class) {
                if(instance == null)
                    instance = new Executor();
            }
        return instance;
    }

    /**
     * 执行一个线程
     * @param runnable
     */
    public void execute(Runnable runnable) {
        if(executorService == null)
            throw new RuntimeException("thread pool is null");
        executorService.execute(runnable);
    }

    public void shutdown() {
        if(executorService == null)
            throw new RuntimeException("thread pool is null");
        executorService.shutdown();
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        if(executorService == null)
            throw new RuntimeException("thread pool is null");
        return (ThreadPoolExecutor) executorService;
    }
}
