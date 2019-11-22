package provider.spring.bean.custompropertyeditor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author JunWu
 * 自定义实现 BeanFactoryPostProcessor 注册自定义解析器
 * 需要将本来注册到Spring中
 */
public class RegisterBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        this.registerByPropertyEditor();
    }


    /**
     * 通过PropertyEditorRegistrar注册
     */
    private void registerByPropertyEditorRegister() {
        beanFactory.addPropertyEditorRegistrar(new DatePropertyRegistrar());
    }

    /**
     * 通过PropertyEditor直接注册,避免了注册PropertyEditorRegistrar
     */
    private void registerByPropertyEditor() {
        beanFactory.registerCustomEditor(Toy.class, Property2ToyEditor.class);
    }
}
