package provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import provider.entity.User;
import provider.feigninterface.UserFeignClient;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/15 0015 13:06
 * <p>
 * 在Feign的客户端上指定了fallback回退方法,同时也在controller中指定了回退方法,
 * 那么将会依次调用,先调用feign中指定的回退方法,在调用controller中回调方法
 */
@RestController
public class MovieController {


    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 为方法启动断路器,方法调用失败则使用findByIdBack方法进行回退 , 一般在feign客户端配置断路器即可
     */
    @HystrixCommand(fallbackMethod = "findByIdBack", commandProperties = {
            //请求超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            //设置统计的时间窗口值的，毫秒值
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
            //设置使用隔离策略
            //@HystrixProperty(name = "execution.isolation.strategy" , value = "THREAD")
    })
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/{id}")
    public User selectById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/get")
    public List<User> get(@RequestParam("id") Long id, @RequestParam String name) {
        List<User> users = userFeignClient.get(id, name);
        return users;
    }

    @GetMapping("/get2")
    public User get2(@RequestParam Map<String, Object> map) {
        return userFeignClient.get2(map);
    }


    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return userFeignClient.post(user);
    }


    public User findByIdBack(Long id) {
        User user = new User();
        user.setAge(null);
        user.setId(-1L);
        user.setName("默认用户");
        user.setUsername("默认用户");
        user.setBalance(null);
        return user;
    }

}
