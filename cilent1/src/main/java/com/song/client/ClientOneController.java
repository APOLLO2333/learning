package com.song.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientOneController {

    @Autowired
    private ClientOneService clientOneService;

    @GetMapping("/hello")
    public String hello() {
        return clientOneService.hello();
    }
}
