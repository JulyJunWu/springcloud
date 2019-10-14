package com.ws.cloud.userprovider.controller;

import com.ws.cloud.userprovider.dao.UserRepository;
import com.ws.cloud.userprovider.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


}
