package com.ws.cloud.consumer.controller;

import com.ws.cloud.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/15 0015 13:06
 * @Copyright: Fujian Linewell Software Co., Ltd. All rights reserved.
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public User findByUserId(@PathVariable Long id) {
        return restTemplate.getForObject("http://127.0.0.1:8080/user/" + id, User.class);
    }

}
