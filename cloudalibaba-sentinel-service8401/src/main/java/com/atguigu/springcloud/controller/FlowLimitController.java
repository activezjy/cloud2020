package com.atguigu.springcloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+".......testB");
        return "------testB";
    }

    @GetMapping("testD")
    public String testD(){
        //暂停几秒钟线程
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        log.info("testD 测试Rt");
        log.info("tetsD 异常比例");
        int age = 10/0;
        return "-------------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotkey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        int age = 10/0;
        return "--------------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "--------deal_testHotKey,/(ㄒoㄒ)/~~";
    }
}
