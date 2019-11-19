package provider.spring.bean.tx.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import provider.spring.bean.jdbc.dao.UserRowMapper;
import provider.spring.bean.jdbc.entity.User;
import provider.spring.bean.jdbc.service.UserService;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author JunWu
 */
public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    private UserService userService2;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setUserService2(UserService userService) {
        this.userService2 = userService;
    }

    /**
     * rollbackFor : 指定哪些异常类型需要回滚
     *
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user (age,name,sex) values (?,?,?)", new Object[]{user.getAge(), user.getName(), user.getSex()});
        userService2.save(user);
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from user", new UserRowMapper());
    }
}
