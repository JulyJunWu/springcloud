package provider.feigninterface;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;
import provider.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/16 0016 11:24
 * @FeignClient : 注解中的microservice-user-provider是一个任意的客户端名称，用于创建Ribbon负载均衡器。
 * 还可使用url 属性指定请求的URL ( URL可以是完整的URL或者主机名) ,例如
 * @FeignClient(name="microservice-user-provider",url="http://locahost:8000/"). 由代码可知，只须使用@FeignClient注解的 fallback属性，就可为指定名称的Feign客户端添加回退。
 *
 *
 * FeignClient注解的属性fallback和fallbackFactory二选一 ,前者不提供错误异常, 后者提供错误异常;
 *
 */
@FeignClient(name = "microservice-user-provider", fallbackFactory = FeignClientFallbackFactory.class)
@Primary
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    /**
     * 使用feign构造多参数请求
     * get请求多参数
     */
    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    List<User> get(@RequestParam("id") Long id, @RequestParam("name") String name);

    /**
     * 使用feign构造多参数请求
     * <p>
     * get请求多参数
     */
    @RequestMapping(value = "/user/get2", method = RequestMethod.GET)
    User get2(@RequestParam Map<String, Object> map);


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    User post(@RequestBody User user);

}
