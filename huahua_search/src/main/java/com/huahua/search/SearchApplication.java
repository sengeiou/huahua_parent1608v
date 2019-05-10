package com.huahua.search;

import huahua.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

/**
 * @ClassName: SearchApplication
 * @Author: zhangyuyang
 * @Time : 2019/4/19 -下午4:46
 * @Version:1.0
 */
@SpringBootApplication
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
