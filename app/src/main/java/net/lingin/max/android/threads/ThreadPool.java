package net.lingin.max.android.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by: var_rain.
 * Created date: 2018/10/20.
 * Description: 线程池
 */
public class ThreadPool {

    /* 线程池 */
    private static ExecutorService threadPool;

    static {
        threadPool = Executors.newCachedThreadPool();
    }

    /**
     * 添加线程到线程池
     *
     * @param thread 线程
     */
    public static void execute(Runnable thread) {
        if (threadPool != null) {
            threadPool.execute(thread);
        }
    }

    /**
     * 关闭线程池
     */
    public static void destroy() {
        if (threadPool != null) {
            if (!threadPool.isShutdown()) {
                threadPool.shutdown();
            }
        }
    }
}
