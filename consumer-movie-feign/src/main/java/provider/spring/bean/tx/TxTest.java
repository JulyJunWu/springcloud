package provider.spring.bean.tx;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import provider.spring.bean.jdbc.entity.User;
import provider.spring.bean.jdbc.service.UserService;

/**
 * @author JunWu
 * 事物测试
 */
@Slf4j
public class TxTest {

    public static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/tx.xml");

    @Test
    public void test() throws Exception{
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save(new User(0,"TT",18,"女"));
    }


}
