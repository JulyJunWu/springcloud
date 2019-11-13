package provider.spring.bean.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author JunWu
 * 自定义感兴趣的事件
 */
@Slf4j
public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }

    public void say() {
        log.info("{}", CustomEvent.class.getName());
    }
}
