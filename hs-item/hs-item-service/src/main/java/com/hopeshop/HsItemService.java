package com.hopeshop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hopeshop.item.mapper")
public class HsItemService {

    public static void main(String[] args) {
        SpringApplication.run(HsItemService.class,args);
    }
}
