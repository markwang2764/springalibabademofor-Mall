package com.example.springcloudalibabauserweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.springcloudalibabauserweb.dao")
@EnableWebMvc
public class SpringcloudalibabaUserWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudalibabaUserWebApplication.class, args);
    }

}
