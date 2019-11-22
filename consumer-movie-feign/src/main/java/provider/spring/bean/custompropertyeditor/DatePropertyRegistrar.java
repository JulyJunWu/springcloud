package provider.spring.bean.custompropertyeditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.util.Date;

/**
 * @author JunWu
 */
public class DatePropertyRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(Date.class, new DatePropertyEditor());
        //注册自定义的解析类
        registry.registerCustomEditor(Toy.class, new Property2ToyEditor());
    }
}
