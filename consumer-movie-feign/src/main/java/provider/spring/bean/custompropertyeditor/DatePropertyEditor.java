package provider.spring.bean.custompropertyeditor;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  JunWu
 * 自定义属性解析
 *  使用场景:
 *     <bean>
 *         <property name="date" value="2018-10-09"></property>
 *     </bean>
 *     默认不支持date注入,所以需要我们自定义一个解析成date的函数
 */
@Setter
@Slf4j
@Getter
public class DatePropertyEditor extends PropertyEditorSupport {

    private String format = "yyyy-MM-dd";

    /**
     * 转换并设置属性值
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try{
            date = simpleDateFormat.parse(text);
        }catch (ParseException p){
            log.error(p.getMessage());
        }
        this.setValue(date);
    }
}
