package provider.spring.bean.listener;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JunWu
 * 测试spring观察者事件
 */
public class CustomListenerTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/circleReference.xml");
        applicationContext.publishEvent(new CustomEvent("Six"));
    }

}
