package com.huahua.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 编写启动类
 * @ClassName: EurekaServerApplication
 * @Author: zhangyuyang
 * @Time : 2019/4/28 -上午9:58
 * @Version:1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
