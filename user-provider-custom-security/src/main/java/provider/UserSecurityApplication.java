package provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 可以使用@EnableEurekaClient注解替代@EnableDiscoveryClient。在Spring Cloud中，服务发现组件有多种选择，例如Zookeeper、Consul等。
 * @EnableDiscoveryClient 为各种服务组件提供了支持，该注解是spring cloud-commons项目的注解，是一个高度
 * 的抽象;而@EnableEurekaClient 表明是Eureka 的Client,该注解是spring cloud-netflix
 * 项目中的注解，只能与Eureka一起工作。当Eureka在项目的casspath 中时，两个注解没有区别。
 */

@SpringBootApplication
@EnableDiscoveryClient
public class UserSecurityApplication {

    @Bean
    @LoadBalanced  //开启本地负载均衡
    public RestTemplate createTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserSecurityApplication.class, args);
    }


}
