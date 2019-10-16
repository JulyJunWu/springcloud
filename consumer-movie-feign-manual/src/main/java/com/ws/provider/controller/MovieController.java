package com.ws.provider.controller;

import com.ws.provider.entity.User;
import com.ws.provider.feigninterface.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/15 0015 13:06
 * @Import 导人的FeignClientsConfiguration是Spring Cloud为Feign默认提共的配置类。
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {

    private UserFeignClient userFeignClient;

    private UserFeignClient adminFeignClient;

    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {

        this.userFeignClient = Feign.builder().client(client).contract(contract).encoder(encoder).decoder(decoder).
                requestInterceptor(new BasicAuthRequestInterceptor("user", "password1"))
                .target(UserFeignClient.class, "http://microservice-user-provider/");

        this.adminFeignClient = Feign.builder().client(client).contract(contract).encoder(encoder).decoder(decoder).
                requestInterceptor(new BasicAuthRequestInterceptor("admin", "password1"))
                .target(UserFeignClient.class, "http://microservice-user-provider/");
    }


    @GetMapping("/user/{id}")
    public User findByIdUser(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/admin/{id}")
    public User findByIdAdmin(@PathVariable Long id) {
        return adminFeignClient.findById(id);
    }

}
