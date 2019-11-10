package provider.spring.bean.customtag;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author JunWu
 * 定义一个类用来解析xsd文件中的定义和组件的定义
 * 其实就是用来解析自定义标签的每个属性
 */
public class CoderBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    /**
     * element 对应的类
     *
     * @param element
     * @return
     */
    @Override
    protected Class<?> getBeanClass(Element element) {
        return Coder.class;
    }

    /**
     * 从element解析并提取对应的元素
     *
     * @param element
     * @param builder
     */
    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        //  this.injectionByProperty(element, builder);
        this.injectionByConstructor(element, builder);
    }

    /**
     * 通过属性注入
     */
    private void injectionByProperty(Element element, BeanDefinitionBuilder builder) {
        String age = element.getAttribute("age");
        String name = element.getAttribute("name");
        // 将提取的数据放入到BeanDefinitionBuilder中,待到完成所有bean的解析后统一注册到
        if (StringUtils.hasText(age)) {
            builder.addPropertyValue("age", age);
        }
        if (StringUtils.hasText(name)) {
            builder.addPropertyValue("name", name);
        }
    }

    /**
     * 通过构造器注入
     */
    private void injectionByConstructor(Element element, BeanDefinitionBuilder builder) {
        String age = element.getAttribute("age");
        String name = element.getAttribute("name");
        //  该方法是通过索引进行构造器注入的
        builder.addConstructorArgValue(age);
        builder.addConstructorArgValue(name);
    }
}
