package com.song.redis;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSyTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AgeAddService ageAddService;

    @Autowired
    private TaskExecutor taskExecutor;

    @SneakyThrows
    @Test
    public void redis() {
        Person p = new Person(1, "song", 0);
        for (int i = 0; i < 1000; i++) {
            ageAddService.addAge(p);
        }
        Thread.sleep(2000);
        System.out.println(p.getAge());

    }


    public boolean lock() {
        return (boolean) redisTemplate.execute((RedisCallback) redisConnection -> {
            long expireAt = System.currentTimeMillis() + 5000 + 1;
            Boolean acquire = redisConnection.setNX("lock".getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {

                byte[] value = redisConnection.get("lock".getBytes());

                if (Objects.nonNull(value) && value.length > 0) {

                    long expireTime = Long.parseLong(new String(value));

                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = redisConnection.getSet("lock".getBytes(), String.valueOf(System.currentTimeMillis() + 5000 + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    public void unlock() {
        redisTemplate.delete("lock");
    }


}
