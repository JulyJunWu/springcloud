package provider.spring.bean.aop;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JunWu
 * AOP测试
 */
public class AopTest {

    @Test
    public void aopTest(){

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/aop.xml");

        TestBean testBean = applicationContext.getBean("testBean",TestBean.class);

        testBean.test();

    }

}
