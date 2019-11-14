package provider.spring.bean.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author JunWu
 * 方法拦截器
 */
@Slf4j
public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        log.info("before...");
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        log.info("after...");

        return invokeSuper;
    }
}
