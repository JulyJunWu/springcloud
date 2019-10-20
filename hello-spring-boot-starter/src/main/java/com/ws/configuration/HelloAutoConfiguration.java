package com.ws.configuration;

import com.ws.service.HelloService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jun
 * data  2019-10-20 19:43
 * 自定义starter
 * 项目打包时,需要删除main函数以及 pom中的SrpingBoot plugin
 */
@Configuration
//启动HelloProperties的配置功能,并加入到IOC容器
@EnableConfigurationProperties({HelloProperties.class})
//在web条件下才成立
//@ConditionalOnWebApplication
@Import(HelloService.class)
public class HelloAutoConfiguration {
}
