package provider.spring.bean.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import provider.spring.bean.custompropertyeditor.Toy;

/**
 * @author JunWu
 * converter转换器 , 把字符串转成对应的类型
 */
public class ToyConverter implements Converter<String, Toy> {

    private static final Toy DEFAULT = new Toy(9.9D, "红色", "中号");

    @Override
    public Toy convert(String source) {
        Toy result = DEFAULT;
        if (!StringUtils.isEmpty(source)) {
            String[] array = source.split("\\|");
            result = new Toy(Double.valueOf(array[0]), array[1], array[2]);
        }
        return result;
    }
}
