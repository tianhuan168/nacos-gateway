package com.example.client2.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianhuan
 * @version 1.0
 * @Description TODO
 * @date 2020-04-30 14:39
 **/

@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

   @GetMapping(value = "/test1")
    public Object test1() throws InterruptedException {
       byte x = 1;
       double y = 2D;
       double v = (short) x / y * 2;
       Thread.sleep(5*1000);
       return v;
   }


    @GetMapping(value = "/path")
    public Map<String,String> path(){
        Map<String, String> map = new HashMap<>(1);
        map.put("1","hello world");
        return map;
    }




}
