package provider.spring.bean.jdbc.service;

import provider.spring.bean.jdbc.entity.User;

import java.util.List;

/**
 * @author JunWu
 * user service接口
 */
public interface UserService {
    /**
     * 插入user数据
     *
     * @param user
     */
    void save(User user);

    /**
     * 获取所有user
     *
     * @return
     */
    List<User> getUsers();
}
