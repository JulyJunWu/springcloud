package provider.spring.bean.custompropertyeditor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author JunWu
 * 用来将属性解析成对应的实体类
 */
@Slf4j
public class Property2ToyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        log.info("{}", text);

        if (StringUtils.isEmpty(text)) {
            this.setValue(new Toy(9.9D, "红色", "大号"));
            return;
        }

        String[] split = text.split("\\|");
        this.setValue(new Toy(Double.parseDouble(split[0]), split[1], split[2]));
    }
}
