package provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import provider.dao.UserRepository;
import provider.entity.User;

import java.util.Collection;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/16 0016 12:56
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable("id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails){
            UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

            for (GrantedAuthority grantedAuthority : authorities){
                log.info("当前用户 : {},  密码 : {}, role : {}",userDetails.getUsername(),userDetails.getPassword(),grantedAuthority.getAuthority());
            }
        }

        return userRepository.findOne(id);
    }
}
