package com.example.gateway;

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

    @Value("${msg:原消息}")
    private String msg;

    @Value("${user:用户}")
    private String user;


    @Value("${name:人名}")
    private String name;

    /**
     * 请描述该方法的意图
     *
     * @author tianhuan
     * @param s
     * @date 2020/5/8 11:23
     * return
     * @version 3.2
     */
    @GetMapping(value = "/config")
    public Object config(String s) {
        logger.info(msg);
        logger.info(user);
        logger.info(name);
        return msg;
    }


}
