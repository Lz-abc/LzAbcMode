package com.abc.lzabcmode.utils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理器
 * Created by xl on 2017/5/17.
 */

public class ThreadManager {


    /**
     * 单例获取线程池
     */
    private static ThreadPool threadPool;

    public static ThreadPool getInstance(int corePoolSize) {

        if (threadPool == null) {
            synchronized (ThreadManager.class) {
                if (threadPool == null)
                    threadPool = new ThreadPool(corePoolSize);
            }
        }
        return threadPool;
    }

    /**
     * 线程池对象
     */
    public static class ThreadPool {

        //核心线程数
        private int corePoolSize;

        private ThreadPoolExecutor executor;

        private ThreadPool(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public void excute(Runnable r) {
            if (executor == null) {
                executor = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
            }
            //执行runnable
            executor.execute(r);
        }

    }

}
