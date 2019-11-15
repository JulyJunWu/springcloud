package provider.spring.bean.jdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import provider.spring.bean.jdbc.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author JunWu
 * @see RowMapperResultSetExtractor#extractData(ResultSet) 后续在此调用
 */
public class UserRowMapper implements RowMapper {

    /**
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     *
     */

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String sex = resultSet.getString("sex");
        return new User(id, name, age, sex);
    }
}
