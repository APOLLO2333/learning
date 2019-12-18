package com.song.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AgeAddService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    @Async
    public void addAge(Person person) {
        try {
            boolean flag = true;
            while (flag) {
                if (lock()) {
                    person.setAge(person.getAge() + 1);
                    log.info("age:{}", person.getAge());
                    flag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
    }


    //tryLock的三个参数含义
    //1.等待锁时间
    //2.拥有锁时间
    //3.时间的单位

    @Async
    public void addAgeRedisson(Person person) {
        RLock lock = redissonClient.getLock("lockRedisson");
        try {
            if (lock.tryLock(30, 5, TimeUnit.SECONDS)) {
                person.setAge(person.getAge() + 1);
                log.info("age:{}", person.getAge());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
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
