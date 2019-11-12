package provider.spring.bean.custompropertyeditor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.CustomEditorConfigurer;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JunWu
 * 使用继承方式注册 属性解析(也可以直接实现接口BeanFactoryPostProcessor,重写方法直接在里面进行注册)
 *
 * 由于使用xml形式注入customEditors属性失败,所以使用继承的方式重写postProcessBeanFactory函数,
 * 并在此函数开头添加对应的属性解析,以供后续注册到BeanFactory中
 *
 * customEditors : 该属性存放 期望类型 -> 解析器
 *  意思为 当注入的时候是此期望类型的时候,会使用该期望类型映射的解析器对值进行解析,如下:
 *      <bean>
 *          <property name="date" value="2019-10-09"></property>
 *      </bean>
 *      期望属性是Date类型, 如果不进行任何的转换,那么注入的属性为String,程序报错,
 *      当我们指定了属性解析,那么就会调用自定义的解析器将String类型转换为Date属性
 *
 */
@Slf4j
public class MyCustomEditorConfigurer extends CustomEditorConfigurer {

    public MyCustomEditorConfigurer() {
        log.info("1111");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //  register(beanFactory);
        //  registerDirect(beanFactory);
        registerByRegistrar(beanFactory);
    }

    /**
     * 方式一 : 原生注册
     * @param beanFactory
     */
    private void register(ConfigurableListableBeanFactory beanFactory){
        Map<Class<?>, Class<? extends PropertyEditor>> map = new HashMap<>(2);
        map.put(Date.class, DatePropertyEditor.class);
        this.setCustomEditors(map);
        super.postProcessBeanFactory(beanFactory);
    }

    /**
     * 方式一 : 直接注册,无需依靠父类注册
     * @param beanFactory
     */
    private void registerDirect(ConfigurableListableBeanFactory beanFactory){
        beanFactory.registerCustomEditor(Date.class,DatePropertyEditor.class);
    }

    /**
     * 方式二: 通过Registrar进行注册,参考 父类属性 propertyEditorRegistrars
     */
    private void registerByRegistrar(ConfigurableListableBeanFactory beanFactory){
        beanFactory.addPropertyEditorRegistrar(new DatePropertyRegistrar());
    }

}
