package provider.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * @author JunWu
 * 替换方法执行需要实现此接口 MethodReplacer
 */
@Slf4j
public class MethodReplace implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        log.info("成功替换 , o : {} , method : {} " , o.getClass().getName(),method.getName());
        return null;
    }
}
