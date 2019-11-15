package provider.spring.bean.jdbc.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import provider.spring.bean.jdbc.dao.UserRowMapper;
import provider.spring.bean.jdbc.entity.User;
import provider.spring.bean.jdbc.service.UserService;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * @author JunWu
 * user服务实现类
 */
public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user (name,age,sex) values (?,?,?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR
                });
    }

    @Override
    public List<User> getUsers() {

        //  Integer user = jdbcTemplate.queryForObject("select age from user where id = 1", Integer.class);
        //  List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");

        return jdbcTemplate.query("select * from user where age = ? ", new Object[]{18},new int[]{Types.INTEGER}, new UserRowMapper());
    }
}
