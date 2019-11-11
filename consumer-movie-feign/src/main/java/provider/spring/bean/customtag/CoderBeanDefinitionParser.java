package provider.spring.bean.customtag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JunWu
 * 定义一个类用来解析xsd文件中的定义和组件的定义
 * 其实就是用来解析自定义标签的每个属性
 */
@Slf4j
public class CoderBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    static Map<String, Class> SHORT_NAME_MAPPINGS = new ConcurrentHashMap<>(4);

    /**
     * getBeanClassName 和 getBeanClass 可二选一
     * @param element
     * @return
     */
    @Override
    protected String getBeanClassName(Element element) {
        return element.getAttribute("class");
    }

    /**
     * element 对应的类
     *
     * @param element
     * @return
     */
    @Override
    protected Class<?> getBeanClass(Element element) {
        //获取命名空间，如 <myname:coder> => coder
        String namespaceURI = element.getNamespaceURI();
        log.info("命名空间 -> {}", namespaceURI);
        String aClass = element.getAttribute("class");
        Class<?> classInstance = null;
        try {
            classInstance = SHORT_NAME_MAPPINGS.get(aClass);
            if (classInstance == null) {
                classInstance = Class.forName(aClass);
                SHORT_NAME_MAPPINGS.put(aClass, classInstance);
            }
        } catch (ClassNotFoundException e) {
            log.error("{} -> ClassNotFoundException", e.getMessage());
        }
        return classInstance;
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

        String id = element.getAttribute("id");
        builder.addPropertyValue("id", id);
    }
}
