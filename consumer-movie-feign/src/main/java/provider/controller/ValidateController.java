package provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.entity.User;

import javax.validation.Valid;

/**
 * @author JunWu
 */
@RestController
@RequestMapping("/valid")
public class ValidateController {

    @GetMapping("/user")
    public User validUser(@Valid User user) {
        return user;
    }

    @GetMapping("/hello")
    public User hello(User user){
        return user;
    }

    @GetMapping("/test")
    public String hello(String username){
        return username;
    }

}
