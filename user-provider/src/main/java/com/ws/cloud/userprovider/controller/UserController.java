package com.ws.cloud.userprovider.controller;

import com.ws.cloud.userprovider.dao.UserRepository;
import com.ws.cloud.userprovider.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jun
 * data  2019-10-14 23:54
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findOne(id);
    }


    @GetMapping("/user/get")
    public List<User> get(@RequestParam("id") Long id, @RequestParam("name") String name) {
        return userRepository.findByIdAndName(id, name);
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        Example<User> userExample = Example.of(user);
        return userRepository.findOne(userExample);
    }


}
