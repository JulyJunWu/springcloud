package provider.spring.bean.converter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.support.DefaultConversionService;

public class CustomConversionService extends DefaultConversionService implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        this.addConverter(new ToyConverter());
    }
}
