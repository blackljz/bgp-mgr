package com.bgp.mgr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bgp.mgr.dao")
public class BgpMgrApplication {

    public static void main(String[] args) {
        SpringApplication.run(BgpMgrApplication.class, args);
    }
}
