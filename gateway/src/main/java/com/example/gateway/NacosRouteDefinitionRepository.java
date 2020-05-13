package com.example.gateway;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tianhuan
 * @version 1.0
 * @Description nacos路由数据源
 * @date 2020-05-06 15:14
 **/
public class NacosRouteDefinitionRepository implements RouteDefinitionRepository {

    private static final Logger log = LoggerFactory.getLogger(NacosRouteDefinitionRepository.class);

    private String routerDataId;

    private ApplicationEventPublisher publisher;

    private NacosConfigProperties nacosConfigProperties;

    public NacosRouteDefinitionRepository(String routerDataId, ApplicationEventPublisher publisher, NacosConfigProperties nacosConfigProperties) {
        this.routerDataId = routerDataId;
        this.publisher = publisher;
        this.nacosConfigProperties = nacosConfigProperties;
        addListener();
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            String content = nacosConfigProperties.configServiceInstance().getConfig(routerDataId, nacosConfigProperties.getGroup(), 5000);
            List<RouteDefinition> routeDefinitions = getListByStr(content);
            return Flux.fromIterable(routeDefinitions);
        } catch (NacosException e) {
            log.error("getRouteDefinitions by nacos error", e);
        }
        return Flux.fromIterable(Collections.EMPTY_LIST);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

    /**
     * 添加Nacos监听
     */
    private void addListener() {
        try {
            nacosConfigProperties.configServiceInstance().addListener(routerDataId, nacosConfigProperties.getGroup(), new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    publisher.publishEvent(new RefreshRoutesEvent(this));
                }
            });
        } catch (NacosException e) {
            log.error("nacos-addListener-error", e);
        }
    }

    /**
     * 解析路由
     * @param content
     * @return
     */
    private List<RouteDefinition> getListByStr(String content) {
        if (StringUtils.isNotEmpty(content)) {
            return JSONObject.parseArray(content, RouteDefinition.class);
        }
        return new ArrayList<>(0);
    }
}
