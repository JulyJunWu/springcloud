package provider.spring.bean.beanpostprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author JunWu
 * 自定义后置处理器
 */
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        log.info("MyBeanPostProcessor init");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("bean -> {}  ,  postProcessBeforeInitialization", beanName);
        return bean;

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("bean -> {} ,  postProcessAfterInitialization", beanName);
        return bean;
    }
}
