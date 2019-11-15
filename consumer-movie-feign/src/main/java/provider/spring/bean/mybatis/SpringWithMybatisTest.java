package provider.spring.bean.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import provider.spring.bean.jdbc.entity.User;
import provider.spring.bean.mybatis.dao.UserMapper;

/**
 * @author JunWu
 * mybatis整合spring 测试
 */
@Slf4j
public class SpringWithMybatisTest {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/spring-mybatis.xml");

    @Test
    public void test() {
        UserMapper mapper = applicationContext.getBean("userMapper", UserMapper.class);
        User user = mapper.selectOne(1);
        log.info("{}", user);
    }


}
