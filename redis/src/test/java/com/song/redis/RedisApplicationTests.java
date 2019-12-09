package com.song.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void string() {
        stringRedisTemplate.opsForValue().set("song", "bo");

        System.out.println(stringRedisTemplate.opsForValue().get("song"));
    }

    @Test
    public void hash() {
        Person person = new Person(1, "song", 2);
        redisTemplate.opsForHash().put("person:" + person.getId(), "id", person.getId());
        redisTemplate.opsForHash().put("person:" + person.getId(), "name", person.getName());
        redisTemplate.opsForHash().put("person:" + person.getId(), "age", person.getAge());
        System.out.println(redisTemplate.opsForHash().get("person:" + person.getId(), "age"));

        redisTemplate.opsForHash().put("person:" + person.getId(), "age", person.getAge() + 1);
        System.out.println(redisTemplate.opsForHash().get("person:" + person.getId(), "age"));
    }

    @Test
    public void list() {
        for (int i = 0; i < 3; i++) {
            redisTemplate.opsForList().leftPush("keyList", i);
        }

        while (redisTemplate.hasKey("keyList")){
            System.out.println(redisTemplate.opsForList().rightPop("keyList"));
        }

    }

    @Test
    public void set(){
        redisTemplate.opsForSet().add("setList","1","2","3");
        redisTemplate.opsForSet().add("setList","3");
        redisTemplate.opsForSet().remove("setList","2");
        System.out.println(redisTemplate.opsForSet().size("setList"));
        redisTemplate.opsForSet().add("setList","4","5","6");
        System.out.println(redisTemplate.opsForSet().pop("setList"));
        System.out.println(redisTemplate.opsForSet().size("setList"));
        System.out.println(redisTemplate.opsForSet().pop("setList",2));

//        redisTemplate.opsForSet().difference();  差集
//        redisTemplate.opsForSet().intersect();   交集
//        redisTemplate.opsForSet().union();       并集

//        redisTemplate.opsForSet().differenceAndStore();   差集并存储
    }


    @Test
    public void zSet(){

    }

    //RDB快照，二进制存储，快照方式，一定时间执行一次，占用内存率高

    //AOF 只要是写命令，就会将写命令写入硬盘，效率低

}
