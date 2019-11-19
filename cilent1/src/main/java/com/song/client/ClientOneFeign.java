package com.song.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "clientTo",fallback = Back.class)
public interface ClientOneFeign {
    @GetMapping("/hello")
    String hello();
}
