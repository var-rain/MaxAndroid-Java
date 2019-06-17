package net.lingin.max.android.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by: var_rain.
 * Created date: 2018/10/20.
 * Description: 线程池
 */
public class Worker {

    private static ExecutorService service;

    /**
     * 初始化线程池,根据需要创建对应的线程池对象,
     * 使用如下方式是在 {@link android.app.Application} 中或其他位置进行初始化
     * <code>
     * Worker.init(Worker.newCachedThreadPool());
     * </code>
     *
     * @param service 线程池类型,创建对应线程池请参见 {@link Worker} 中的定义
     */
    public static void init(ExecutorService service) {
        Worker.service = service;
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     *
     * @return {@link ExecutorService}
     */
    public static ExecutorService newCachedThreadPool() {
        return Executors.newCachedThreadPool();
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     *
     * @param threads 线程池的最大工作线程数量
     * @return {@link ExecutorService}
     */
    public static ExecutorService newFixedThreadPool(int threads) {
        return Executors.newFixedThreadPool(threads);
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行。
     *
     * @param threads 线程池的最大工作线程数量
     * @return {@link ExecutorService}
     */
    public static ExecutorService newScheduledThreadPool(int threads) {
        return Executors.newScheduledThreadPool(threads);
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     *
     * @return {@link ExecutorService}
     */
    public static ExecutorService newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    /**
     * 添加并执行线程
     *
     * @param thread 线程
     */
    public static void execute(Runnable thread) {
        if (service != null) {
            service.execute(thread);
        }
    }

    /**
     * 关闭线程池
     */
    public static void destroy() {
        if (service != null) {
            if (!service.isShutdown()) {
                service.shutdown();
            }
        }
    }
}
