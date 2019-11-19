package provider.spring.bean.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import provider.spring.bean.jdbc.entity.User;
import provider.spring.bean.jdbc.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 * @author JunWu
 * JDBC测试
 */
@Slf4j
public class JdbcTest {

    private static final String URL = "jdbc:mysql://localhost:3306/ws?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static ApplicationContext applicationContext;

    @Test
    public void originalInsert() throws Exception {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into user (name,age,sex) values ('文思泉涌',18,'女')");
        //  省略关闭资源操作
    }

    @Before
    public void initApplication() {
        applicationContext = new ClassPathXmlApplicationContext("test/jdbc.xml");
    }

    @Test
    public void save(){
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save(new User(0, "纹丝不动", 18, "男"));
    }

    @Test
    public void select() {
        UserService userService = applicationContext.getBean(UserService.class);
        List<User> users = userService.getUsers();
        users.stream().forEach(p -> log.info("{}", p));
    }


}
