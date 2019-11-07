package provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.spring.bean.CustomDestroy;
import provider.spring.bean.SpringApplicationUtil;

/**
 * @author JunWu
 */
@RestController
@RequestMapping("/destroy")
@Slf4j
public class TestDestroyController {

    @GetMapping
    public String test() {
        this.testDestroy();
        return "OK";
    }

    private void testDestroy() {
        BeanFactory beanfactory = SpringApplicationUtil.getBeanfactory();
        if (beanfactory instanceof DefaultListableBeanFactory) {
            this.one(beanfactory);
        }
    }

    /**
     * 销毁方式一
     */
    private void one(BeanFactory beanFactory) {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        CustomDestroy customDestroy = defaultListableBeanFactory.getBean(CustomDestroy.class);
        //这种方式只是调用了下 销毁函数, 并没有将实例从IOC移除
        defaultListableBeanFactory.destroyBean(customDestroy);
        //这种方式也只是调用了下 销毁函数, 并没有将实例从IOC移除
        defaultListableBeanFactory.destroyBean("customDestroy", customDestroy);
        //此方法才是既执行销毁函数, 又将IOC的实例移除 , 后续通过getBean重新创建新实例
        defaultListableBeanFactory.destroySingleton("customDestroy");
        CustomDestroy beanFactoryBean2 = defaultListableBeanFactory.getBean(CustomDestroy.class);
        log.info("{}", customDestroy == beanFactoryBean2);
    }

    private void two(BeanFactory beanFactory) {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;

        CustomDestroy bean = defaultListableBeanFactory.getBean(CustomDestroy.class);

        //  defaultListableBeanFactory.getBeanDefinition()

    }

}
