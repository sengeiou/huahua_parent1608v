package com.huahua.user.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName: ApplicationConfig
 * @Author: zhangyuyang
 * @Time : 2019/4/26 -上午11:34
 * @Version:1.0
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtFilter  jwtFilter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter).addPathPatterns("/**")
                .excludePathPatterns("/**/incfollow/**")
                .excludePathPatterns("/**/incfans/**")
                .excludePathPatterns("/**/login");
    }
}
