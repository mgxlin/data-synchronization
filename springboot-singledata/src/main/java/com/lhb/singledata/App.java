package com.lhb.singledata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lhb
 * @date 2022/2/13 13:39
 */
@SpringBootApplication
@MapperScan("com.lhb.singledata.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
