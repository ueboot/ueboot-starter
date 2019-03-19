package com.ueboot.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * backend 启动类入口
 * @author yangkui
 */
@SpringBootApplication
@EnableTransactionManagement
@EntityScan({
        "com.bstek.uflo.model"
})
@ComponentScan(value = {"com.ueboot.starter","com.ueboot.core","com.bstek.uflo.console"})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 6000, redisNamespace = "backend")
public class BackEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }
}
