package com.footballoddstask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com"}) 改变启动类自动扫描的包
public class FootballOddsTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballOddsTaskApplication.class, args);
    }

}

