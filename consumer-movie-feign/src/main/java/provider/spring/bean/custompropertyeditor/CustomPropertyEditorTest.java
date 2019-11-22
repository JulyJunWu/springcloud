package provider.spring.bean.custompropertyeditor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JunWu
 * 测试自定义属性解析器
 */
@Slf4j
public class CustomPropertyEditorTest {


    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/circleReference.xml");

        ShopRecord shopRecord = applicationContext.getBean(ShopRecord.class);

        log.info("{}", shopRecord.getDate().getTime());

    }

    /**
     * 解析器注入时机 : BeanFactoryPostProcessor
     * 将自身的自定义的解析属性类注册到
     */
    @Test
    public void propertyEditorTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/propertyEditor.xml");
        ShopRecord shopRecord = applicationContext.getBean(ShopRecord.class);
        log.info("{}", shopRecord.getToy());
    }

}
