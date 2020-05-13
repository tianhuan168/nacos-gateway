package com.example.gateway;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tianhuan
 * @version 1.0
 * @date 2020-05-06 15:12
 **/

//@Configuration
public class DynamicRouteConfig {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Value("${spring.cloud.nacos.config.router-data-id:gateway-router.json}")
    private String routerDataId;

    /**
     * Nacos实现方式
     */
    @Configuration
    public class NacosDynRoute {
        @Autowired
        private NacosConfigProperties nacosConfigProperties;

        @Bean
        public NacosRouteDefinitionRepository nacosRouteDefinitionRepository() {
            return new NacosRouteDefinitionRepository(routerDataId,publisher, nacosConfigProperties);
        }
    }

}
