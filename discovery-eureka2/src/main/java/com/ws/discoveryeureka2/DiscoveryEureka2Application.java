package com.ws.discoveryeureka2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DiscoveryEureka2Application {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryEureka2Application.class, args);
    }

}
