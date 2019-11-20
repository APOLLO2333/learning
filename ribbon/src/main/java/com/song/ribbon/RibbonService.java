package com.song.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "HyHello")
    public String hello(){
        return restTemplate.getForObject("http://clientTo/hello",String.class);
    }
    private String HyHello(){
        return "HystrixCommand fallback";
    }
}
