package com.ws.cloud.consumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.ws.cloud.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/15 0015 13:06
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/user/{id}")
    public User findByUserId(@PathVariable Long id) {
        //开启负载均衡后,通过ip访问不可行
        return restTemplate.getForObject("http://192.168.4.13:8080/user/" + id, User.class);
    }

    @GetMapping("/{id}")
    public User selectOne(@PathVariable Long id){
        return restTemplate.getForObject("http://microservice-user-provider/user/" + id, User.class);
    }

    /**
     * 根据服务名称获取实例数据(其中metadata是元数据,可以自定义)
     * @return
     */
    @GetMapping("/instanceInfo")
    public List<ServiceInstance> showInfo(){
        List<ServiceInstance> instances = discoveryClient.getInstances("microservice-user-provider");
        return instances;
    }


}
