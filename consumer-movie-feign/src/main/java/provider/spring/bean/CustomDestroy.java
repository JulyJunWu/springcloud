package provider.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author JunWu
 * bean的销毁方法
 * 实现方式一 : 实现接口 DisposableBean
 */
@Slf4j
@Component
public class CustomDestroy  /*implements DisposableBean */{

    private String name;

    //  @Override
    public void destroy() throws Exception {
       log.info("{} destroy", this.getClass().getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
