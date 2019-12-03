package com.song.mybatis;

import com.song.mybatis.entity.Person;
import com.song.mybatis.service.IPersonService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    //SpringBoot+MyBatis中一级缓存和二级缓存都是开启的，注意二级缓存虽然是开启状态但是需要进行配置才可以使用

    @Autowired
    private IPersonService personService;

    @Test
    public void contextLoads() {
    }


    //MyBatis一级缓存的生命周期和SqlSession一致。
    //MyBatis一级缓存内部设计简单，只是一个没有容量限定的HashMap，在缓存的功能性上有所欠缺。
    //MyBatis的一级缓存最大范围是SqlSession内部，有多个SqlSession或者分布式的环境下，数据库写操作会引起脏数据，建议设定缓存级别为Statement。

    @Test
    @Transactional
    public void mybatisOne() {
        Person p = new Person();
        p.setId(2);
        p.setAge(12);
        personService.list();
        personService.updateById(p);
        personService.list();
    }



    //MyBatis的二级缓存相对于一级缓存来说，实现了SqlSession之间缓存数据的共享，同时粒度更加的细，能够到namespace级别，通过Cache接口实现类不同的组合，对Cache的可控性也更强。
    //MyBatis在多表查询时，极大可能会出现脏数据，有设计上的缺陷，安全使用二级缓存的条件比较苛刻。
    //在分布式环境下，由于默认的MyBatis Cache实现都是基于本地的，分布式环境下必然会出现读取到脏数据，需要使用集中式缓存将MyBatis的Cache接口实现，有一定的开发成本，直接使用Redis、Memcached等分布式缓存可能成本更低，安全性也更高。

    @Test
    public void mybatisTwo() {
        Person p = new Person();
        p.setId(2);
        p.setAge(12);
        personService.list();
        personService.updateById(p);
        personService.list();
    }


}
