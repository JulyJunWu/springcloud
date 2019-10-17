package com.ws.gateway;

import com.netflix.zuul.ZuulFilter;
import com.ws.gateway.filter.CustomZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author Jun
 * data  2019-10-16 21:06
 * <p>
 * 在启动类上添加注解@EnableZuulProxy，声明一个Zuul代理。该代理使用Ribbon来
 * 定位注册在Eureka Server中的微服务;同时，该代理还整合了Hystrix,从而实现了
 * 容错，所有经过Zuul的请求都会在Hystrix命令中执行。
 * <p>
 * 访问http://localhost:8100/microservice-consumer-movie/1 ,请求会被转发到tp://localhost:8082/1(用户微服务)。
 * 说明默认情况下，Zuul 会代理所有注册到Eureka Server的微服务，并且Zul的路
 * 由规则如下:
 * http://ZUUL HOST:ZUUL PORT/微服务在Eureka上的serviceId/**会被转发到serviceld
 * 对应的微服务。
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayZuulApplication {

    /**
     * 配置自定义过滤器
     */
    @Bean
    public CustomZuulFilter custom() {
        return new CustomZuulFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulApplication.class, args);
    }

}
