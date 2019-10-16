package provider.feigninterface;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import provider.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/16 0016 17:23
 * @FeignClient 注解的属性fallback和fallbackFactory二选一 ,
 *  前者不提供错误异常, 后者提供错误异常;
 */
@Component
@Slf4j
public class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    private static User user = new User();

    @Override
    public UserFeignClient create(Throwable cause) {

        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                this.print();
                return user;
            }

            @Override
            public List<User> get(Long id, String name) {
                this.print();
                return Collections.emptyList();
            }

            @Override
            public User get2(Map<String, Object> map) {
                this.print();
                return user;
            }

            @Override
            public User post(User user) {
                this.print();
                return user;
            }

            private void print() {
                if (cause != null){
                    log.error(cause.getMessage());
                }
            }
        };
    }
}
