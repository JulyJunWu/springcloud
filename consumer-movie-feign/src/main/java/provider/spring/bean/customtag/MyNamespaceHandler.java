package provider.spring.bean.customtag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author JunWu
 * 创建一个Handler 文件，护展自NamespaceHandlerSupport, 目的是将组件注册到Spring容器。
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("coder", new CoderBeanDefinitionParser());
    }
}
