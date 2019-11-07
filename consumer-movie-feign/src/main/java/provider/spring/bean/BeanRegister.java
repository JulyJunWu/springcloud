package provider.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JunWu
 * 统一注册bean
 */
@Configuration
public class BeanRegister {

    @Bean
    public CustomDestroy customDestroy(){
        return new CustomDestroy();
    }

    @Bean
    public SpringApplicationUtil springApplicationUtil(){
        return new SpringApplicationUtil();
    }

}
