package com.hopeshop.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hopeshop.user.mapper")
public class HsUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(HsUserApplication.class, args);
    }
}
