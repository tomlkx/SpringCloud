package org.lkx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicationConsumer8080 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumer8080.class, args);
    }
}