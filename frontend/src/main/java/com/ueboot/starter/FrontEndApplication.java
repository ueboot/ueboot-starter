package com.ueboot.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * api应用 启动类入口
 * @author yangkui
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(value = {"com.ueboot.starter","com.ueboot.core"})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 6000, redisNamespace = "frontend")
public class FrontEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontEndApplication.class, args);
    }
}
