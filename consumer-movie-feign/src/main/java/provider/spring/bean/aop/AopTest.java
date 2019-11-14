package provider.spring.bean.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @author JunWu
 * AOP测试
 */
@Slf4j
public class AopTest {

    @Test
    public void aopTest() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/aop.xml");

        TestBean testBean = applicationContext.getBean("testBean", TestBean.class);

        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("testBean");

        Object attribute = beanDefinition.getAttribute(AutoProxyUtils.ORIGINAL_TARGET_CLASS_ATTRIBUTE);

        log.info("原生类型 -> {}", attribute);

        testBean.test();

        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        userService.perform();

    }

    /**
     * JDK动态代理
     * 基于接口实现
     */
    @Test
    public void jdkDynamicProxy() {
        UserServiceImpl userService = new UserServiceImpl();
        UserInvocationHandler userInvocationHandler = new UserInvocationHandler(userService);

        IUserService proxyInstance = userInvocationHandler.getProxy();
        proxyInstance.perform();

        //验证是否是代理类
        boolean proxyClass = Proxy.isProxyClass(proxyInstance.getClass());
        log.info("{}是否代理类 -> {}", proxyInstance.getClass(), proxyClass);
    }

    /**
     * 基于cglib代理
     */
    @Test
    public void cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestBean.class);
        enhancer.setCallback(new MethodInterceptorImpl());

        TestBean o = (TestBean) enhancer.create();
        o.test();
    }

}
