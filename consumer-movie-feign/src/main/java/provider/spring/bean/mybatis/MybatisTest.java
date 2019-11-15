package provider.spring.bean.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import provider.spring.bean.jdbc.entity.User;
import provider.spring.bean.mybatis.dao.UserMapper;

import java.io.Reader;

/**
 * @author JunWu
 * mybatis 测试
 */
@Slf4j
public class MybatisTest {

    public static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("test/mybatis/config/sqlConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void originalQuery() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectOne(2);
        log.info("{}", user);
    }

    @Test
    public void originalInsert() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.insertUser(new User(0, "Hello", 19, "男"));
        sqlSession.commit();
    }

}
