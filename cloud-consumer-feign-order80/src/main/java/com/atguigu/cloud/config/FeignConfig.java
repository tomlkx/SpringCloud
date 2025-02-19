package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer() {
        // 下面这行是Feign默认的重试策略，表示不进行任何重试。这里被注释掉了。
        return Retryer.NEVER_RETRY;

        // 使用Retryer.Default构造函数创建一个新的重试策略对象。
        // 参数1: 100 - 初始等待时间（毫秒），即在第一次重试之前需要等待的时间。
        // 参数2: 1000 - 最大等待时间（毫秒），即在每次重试之间的等待时间的最大值。
        // 参数3: 3 - 尝试的最大次数，包括首次调用。这里设置为3意味着总共会有1次初始调用加上最多2次重试。
        //return new Retryer.Default(100, 1000, 3);
    }
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}