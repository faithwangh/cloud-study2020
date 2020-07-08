package com.example.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    //bean 用注解是方式 依赖注入实例到ioc容器里
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
