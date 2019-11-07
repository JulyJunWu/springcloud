package provider.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author JunWu
 * 获取上下文工具类
 */
public class SpringApplicationUtil implements ApplicationContextAware , BeanFactoryAware {

    public static ApplicationContext applicationContext = null;

    public static BeanFactory beanfactory = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringApplicationUtil.beanfactory = beanFactory;
    }

    public static BeanFactory getBeanfactory() {
        return beanfactory;
    }
}
