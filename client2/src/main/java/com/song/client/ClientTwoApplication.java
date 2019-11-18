package com.song.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientTwoApplication.class, args);
    }

}
