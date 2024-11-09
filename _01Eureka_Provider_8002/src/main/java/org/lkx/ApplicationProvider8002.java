package org.lkx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApplicationProvider8002 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider8002.class, args);
    }
}