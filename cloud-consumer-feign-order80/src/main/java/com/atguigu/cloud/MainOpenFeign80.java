package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient // 该注解用于向Consul为注册中心时注册服务
@SpringBootApplication
@EnableFeignClients // 启动feign客户端,定义服务+绑定接口,以声明式的方法优雅简单的时间服务调用
public class MainOpenFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(MainOpenFeign80.class);
    }
}