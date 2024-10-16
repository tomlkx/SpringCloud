package org.lkx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicationProvider8001 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider8001.class, args);
    }
}