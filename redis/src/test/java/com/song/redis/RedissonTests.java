package com.song.redis;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonTests {


    @Autowired
    private AgeAddService ageAddService;

    @SneakyThrows
    @Test
    public void Redisson(){
        Person p = new Person(1, "song", 0);
        for (int i = 0; i < 1000; i++) {
            ageAddService.addAgeRedisson(p);
        }
        Thread.sleep(2000);
        System.out.println(p.getAge());
    }
}
