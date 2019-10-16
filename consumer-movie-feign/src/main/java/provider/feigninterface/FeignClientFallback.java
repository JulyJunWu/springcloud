package provider.feigninterface;

import org.springframework.stereotype.Component;
import provider.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/16 0016 17:14
 */

@Component
public class FeignClientFallback implements UserFeignClient {

    @Override
    public User findById(Long id) {
        return new User();
    }

    @Override
    public List<User> get(Long id, String name) {
        return Collections.emptyList();
    }

    @Override
    public User get2(Map<String, Object> map) {
        return new User();
    }

    @Override
    public User post(User user) {
        return new User();
    }
}