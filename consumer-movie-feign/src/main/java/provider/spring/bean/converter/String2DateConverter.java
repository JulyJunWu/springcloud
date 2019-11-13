package provider.spring.bean.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @author JunWu
 * 自定义属性转换器
 */
@Slf4j
public class String2DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        Date date = null;
        try {
            date = DateUtils.parseDate(s, new String[]{"yyyy/MM/dd","yyyy-MM-dd","yyyy-MM-dd"});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
