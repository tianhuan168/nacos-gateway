package com.example.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianhuan
 * @version 1.0
 * @date 2020-04-30 14:39
 **/

@RestController
@RefreshScope
public class FallbackController {

    private static final Logger logger = LoggerFactory.getLogger(FallbackController.class);


    /**
     * 请描述该方法的意图
     *
     * @author tianhuan
     * @date 2020/5/8 11:23
     * @version 3.2
     */
    @RequestMapping(value = "/fallback")
    public Object config() {
        logger.info("Hello World! \\n from gateway");
        return "Hello World! \\n from gateway";
    }


}
