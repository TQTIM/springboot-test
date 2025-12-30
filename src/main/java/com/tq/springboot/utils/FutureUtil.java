package com.tq.springboot.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * @author tq
 * @date 2025/12/30 16:01
 * @description: 超时处理工具类
 */
public class FutureUtil {
    /**
     * 超时控制
     * 两个 Future 放到一起“赛跑”，realFuture 3 秒内完成 → 用真实结果  timeoutFuture 先触发 → 抛 TimeoutException
     * 用 ScheduledExecutorService 创建一个“会超时失败的 Future”，再用 applyToEither 让真实任务和超时任务竞争完成，从而实现非阻塞、可组合的超时控制
     */
    public static <T> CompletableFuture<T> withTimeout(
            CompletableFuture<T> realFuture,
            long timeout,
            TimeUnit unit,
            ScheduledExecutorService scheduler) {

        CompletableFuture<T> timeoutFuture = new CompletableFuture<>();

        scheduler.schedule(
                () -> timeoutFuture.completeExceptionally(new TimeoutException()),
                timeout,
                unit
        );

        return realFuture.applyToEither(timeoutFuture, Function.identity());
    }
}
