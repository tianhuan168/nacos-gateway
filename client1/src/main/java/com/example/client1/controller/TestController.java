package com.example.client1.controller;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianhuan
 * @version 1.0
 * @date 2020-04-30 14:39
 **/

@RestController
@RefreshScope
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * 请描述该方法的意图
     *
     * @return "java.lang.String"
     * @author tianhuan
     * @date 2020/4/30 15:06
     * @version 3.2
     */

    @Value("${node:节点}")
    private String node;

    @Value("${user:用户}")
    private String user;


    @GetMapping(value = "/test1")
    public String test1() {
        logger.info("test1={}", node);
        logger.info("test1={}", user);
        return "hello world!";
    }

    @GetMapping(value = "/test2")
    public Map<String, String> test2() {
        logger.info("test2={}", node);
        return new HashMap<>();
    }

    /**
     * 请描述该方法的意图
     *
     * @param s
     * @param a
     * @return eeeee
     * @author tianhuan
     * @date 2020/5/8 11:39
     * @version 3.2
     */
    @GetMapping(value = "/test3")
    public List<Integer> test3(String s, int a) {
        logger.info("test3={}", node);
        return new ArrayList<>();
    }


    public static void main(String[] args) {

        List<Integer> a = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            a.add(i);
        }


        List<List<Integer>> partition = Lists.partition(a, 50);

        System.out.println(partition);

    }
}
