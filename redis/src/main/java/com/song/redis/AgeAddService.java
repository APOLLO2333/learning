package com.song.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class AgeAddService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Async
    public Integer addAge(Person person) {
        try {
            boolean flag = true;
            while (flag) {
                if (lock()) {
                    person.setAge(person.getAge() + 1);
                    log.info("age:{}", person.getAge());
                    unlock();
                    flag = false;
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public boolean lock() {
        return (boolean) redisTemplate.execute((RedisCallback) redisConnection -> {
            long expireAt = System.currentTimeMillis() + 5000 + 1;
            Boolean acquire = redisConnection.setNX("lock".getBytes(), String.valueOf(expireAt).getBytes());
//            System.out.println(acquire == null);

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
