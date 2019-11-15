package provider.spring.bean.mybatis.dao;

import provider.spring.bean.jdbc.entity.User;

/**
 * mapper
 */
public interface UserMapper {
    /**
     * 查询
     *
     * @param id
     * @return
     */
    User selectOne(int id);

    /**
     * 新增
     *
     * @param user
     */
    void insertUser(User user);

}
