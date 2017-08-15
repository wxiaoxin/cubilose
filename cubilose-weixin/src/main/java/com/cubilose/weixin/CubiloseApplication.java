package com.cubilose.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by jianxin.wang on 2017/8/15.
 */

@SpringBootApplication
@EnableTransactionManagement
public class CubiloseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CubiloseApplication.class, args);
    }

}
