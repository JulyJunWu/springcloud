package provider.spring.bean.beanpostprocessor;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JunWu
 * 测试BeanPostProcessor 后置处理器
 */
public class BeanPostProcessorTest {

    static ApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new ClassPathXmlApplicationContext("test/circleReference.xml");
    }

    @Test
    public void test(){
        Object bean = applicationContext.getBean(MyBeanPostProcessor.class);
    }
}
