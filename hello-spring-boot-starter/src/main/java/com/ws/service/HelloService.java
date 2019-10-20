package com.ws.service;

import com.ws.configuration.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jun
 * data  2019-10-20 19:41
 */
@Service
public class HelloService {

    @Autowired
    private HelloProperties helloProperties;

    public String hello() {
        return "[" + helloProperties.getName() + "," + helloProperties.getAge() + "," + helloProperties.getHobby() + "]";
    }

}
