package com.hopeshop.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 98050
 * @Time: 2018-10-27 11:36
 * @Feature: 订单服务启动器
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(value = "com.hopeshop.order.mapper")
public class HsOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HsOrderApplication.class, args);
    }
}
