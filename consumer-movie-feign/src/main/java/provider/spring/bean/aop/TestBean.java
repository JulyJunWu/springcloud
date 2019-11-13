package provider.spring.bean.aop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JunWu
 * 测试bean
 */
@Data
@Slf4j
public class TestBean {

    private String testStr = "testStr";

    public void test(){
        log.info("{}",TestBean.class.getName());
    }

}
