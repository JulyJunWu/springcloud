package provider.spring.bean.circle;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JunWu
 * 测试循环引用
 */
@Slf4j
public class CircleReferenceTest {

    static ClassPathXmlApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new ClassPathXmlApplicationContext("test/circleReference.xml");
    }

    @Test
    public void testCircle() {
        CircleA bean = applicationContext.getBean(CircleA.class);
        log.info("{}",bean);
    }

}
