package com.huahua.spit;

import huahua.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: SpitApplication
 * @Author: zhangyuyang
 * @Time : 2019/4/17 -上午11:58
 * @Version:1.0
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);
    }
 @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
 }

}
