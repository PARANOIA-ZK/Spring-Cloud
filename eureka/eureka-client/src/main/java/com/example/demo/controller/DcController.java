package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author PARANOIA_ZK
 * @date 2017/12/13 14:34
 */
@RestController
public class DcController {

    Logger logger = LoggerFactory.getLogger(DcController.class);

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() throws InterruptedException {
        //注意下面的一行代码，是为了测试服务降级添加的 -- 2000ms
        //Thread.sleep(50000L);
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:" + sleepTime);
        Thread.sleep(sleepTime);

        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }
}
