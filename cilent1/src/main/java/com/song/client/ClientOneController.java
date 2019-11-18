package com.song.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientOneController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("client 2 print");
        return "hello world";
    }
}
