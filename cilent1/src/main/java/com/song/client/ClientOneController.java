package com.song.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientOneController {

    @Autowired
    private ClientOneFeign clientOneFeign;

    @GetMapping("/hello")
    public String hello() {
        return clientOneFeign.hello();
    }
}
