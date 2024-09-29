package com.lbj.trans;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-27
 * @Description:
 */


@SpringBootApplication
@MapperScan("com.lbj.trans.mapper")
public class TransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
}
