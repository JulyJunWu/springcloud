package com.ws.provider.configuration;

import feign.Contract;
import org.springframework.context.annotation.Bean;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/16 0016 14:27
 * 该类为Feign的配置类
 * 注意:不应该在主应用程序上下文的@ComponentScan中。
 */
//@Configuration
public class FeignConfiguration {

    /**
     * 将契约改为feign原生的默认契约。这样就可以使用feign自带的注解了
     */
    @Bean
    public Contract contract() {
        return new Contract.Default();
    }


}
