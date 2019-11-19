package com.song.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientThreeController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("client 3 print");
        return "hello world";
    }
}
