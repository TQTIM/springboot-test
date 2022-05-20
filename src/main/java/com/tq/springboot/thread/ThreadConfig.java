package com.tq.springboot.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * 线程池配置
 *
 **/
public class ThreadConfig {


        // 核心线程池大小
        private static int corePoolSize = 50;

        // 最大可创建的线程数
        private static int maxPoolSize = 200;

        // 队列最大长度
        private static int queueCapacity = 1000;

        // 线程池维护线程所允许的空闲时间
        private static int keepAliveSeconds = 300;


        /**
         * 执行一般任务，定长线程池
         * @return
         */

        public static ThreadPoolTaskExecutor threadPoolTaskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setMaxPoolSize(maxPoolSize);
            executor.setCorePoolSize(corePoolSize);
            executor.setQueueCapacity(queueCapacity);
            executor.setKeepAliveSeconds(keepAliveSeconds);
            // 线程池对拒绝任务(无线程可用)的处理策略
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return executor;
        }



}
