package com.chengzi.commonaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableEurekaClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 6000)
@EnableFeignClients(basePackages = "com")
public class CommonactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonactionApplication.class, args);
    }
}
