package provider.spring.bean.tx.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAttribute;
import provider.spring.bean.jdbc.dao.UserRowMapper;
import provider.spring.bean.jdbc.entity.User;
import provider.spring.bean.jdbc.service.UserService;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author JunWu
 */
public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * rollbackFor : 指定哪些异常类型需要回滚
     * 注意:::
     * 调用方法含有Transactional注解,在方法内部又在此调用本实例方法(此方法也含有Transactional),那么Transactional只会生效一次;
     * 若调用的事其他实例的方法,则Transactional注解不受影响,继续生效!
     *
     * @param user
     */
    @Transactional(rollbackFor = RollBackException.class)
    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user (age,name,sex) values (?,?,?)", new Object[]{user.getAge(), user.getName(), user.getSex()});
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from user", new UserRowMapper());
    }


    public static class RollBackException extends Throwable {
        public RollBackException() {
            super();
        }
    }
}

