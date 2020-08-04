package com.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author : scottxuan
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.service.user.mapper"})
@ComponentScan(basePackages = {"com.scottxuan","com.service.user"})
public class UserApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UserApplication.class);
    }
}
