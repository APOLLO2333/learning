package com.song.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientThreeApplication.class, args);
    }

}
