package com.song.client;

import org.springframework.stereotype.Component;

@Component
public class Back implements ClientOneFeign {
    @Override
    public String hello() {
        return "HystrixCommand fallback";
    }
}
