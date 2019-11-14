package provider.spring.bean.aop;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author JunWu
 */
@Slf4j
public class UserInvocationHandler implements InvocationHandler {
    /**
     * 需要代理的类
     */
    private Object target;

    public UserInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 增强
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("before");
        Object result = method.invoke(target, args);
        log.info("after");
        return result;
    }

    /**
     * 获取的代理类
     */
    public IUserService getProxy() {
        return (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), this.target.getClass().getInterfaces(), this);
    }
}
