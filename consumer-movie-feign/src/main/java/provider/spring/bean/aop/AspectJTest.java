package provider.spring.bean.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author JunWu
 * 定义一个切面
 */
@Aspect
@Slf4j
public class AspectJTest {

    @Pointcut("execution(* *.test(..)) || execution(* *.perform(..))")
    public void test() {
    }

    @Pointcut("execution(* com.*..hello(..))")
    public void ws(){}

    @Before("test()")
    public void before() {
        log.info("before method execute");
    }

    @After("ws()")
    public void after() {
        log.info("after method execute");
    }

    @Around("test()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("before");
        Object proceed = joinPoint.proceed();
        log.info("after");
        return proceed;
    }

}
