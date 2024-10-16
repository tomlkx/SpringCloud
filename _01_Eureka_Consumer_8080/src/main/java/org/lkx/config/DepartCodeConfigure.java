package org.lkx.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
public class DepartCodeConfigure {
    /**
    *@LoadBalance 通常用于微服务架构中的服务发现和负载均衡。
    *当服务提供者有多个实例时，@LoadBalance 可以帮助客户端在这些实例之间分配请求，
    *以实现负载均衡。在使用 @LoadBalance 注解时，客户端会自动从服务注册中心中获取
    *可用的服务实例列表，并使用负载均衡算法选择其中一个实例进行请求处理。
    **/
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return restTemplate;
    }
}