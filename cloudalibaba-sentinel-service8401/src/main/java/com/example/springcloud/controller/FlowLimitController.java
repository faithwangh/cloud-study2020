package com.example.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {

//        try{
//            TimeUnit.MILLISECONDS.sleep(800);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }

        return "--------testA";

    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "   testB \t");
        return "--------testB";
    }

    @GetMapping("/testD")
    public String testD() {

        //测试异常RT
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        log.info(Thread.currentThread().getName() + "   testD 测试RT \t");

        //测试异常比例
        log.info(Thread.currentThread().getName() + "   testD 测试比例 \t");
        int a = 10/0;

        return "--------testD";
    }
}
