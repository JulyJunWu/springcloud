package provider.spring.bean.converter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.support.DefaultConversionService;
import provider.spring.bean.custompropertyeditor.ShopRecord;

import java.util.Date;

/**
 * @author JunWu
 * 测试Converter转换
 */
@Slf4j
public class ConverterTest {

    /**
     * 测试转换服务
     */
    @Test
    public void converter() {
        //  转换服务
        DefaultConversionService conversionService = new DefaultConversionService();
        //  注册转换器
        conversionService.addConverter(new String2DateConverter());

        // 是否可以转换
        boolean convert = conversionService.canConvert(String.class, Date.class);
        log.info("{}", convert);

        //转换解析
        Date date = conversionService.convert("2019-10-09", Date.class);
        log.info("{}", date.getTime());

    }

    /**
     *     <bean id="conversionService" class="provider.spring.bean.converter.CustomConversionService">
     *     </bean>
     */
    @Test
    public void springConverter(){
        //  转换服务
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/converter.xml");
        ShopRecord bean = applicationContext.getBean(ShopRecord.class);
        log.info("{}",bean.getToy());
    }

    /**
     *   <bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
     *       <property name="converters">
     *           <set>
     *               <bean class="provider.spring.bean.converter.ToyConverter"></bean>
     *           </set>
     *       </property>
     *   </bean>
     */
    @Test
    public void testServiceFactoryBean(){
        //  转换服务
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/converter.xml");
        ShopRecord bean = applicationContext.getBean(ShopRecord.class);
        log.info("{}",bean.getToy());
    }

}
