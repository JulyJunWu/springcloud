package provider.spring.bean.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author JunWu
 * 定义一个监听对象,监听自身感兴趣的事件
 */
public class CustomListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof CustomEvent) {
            CustomEvent testEvent = (CustomEvent) applicationEvent;
            testEvent.say();
        }
    }
}
