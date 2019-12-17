package com.song.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperApplicationTests {

    @Test
    public void contextLoads() {
    }

    private ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper("120.78.149.155:8040", 20000, watchedEvent -> {

        });
    }

    @Test
    public void exists() throws KeeperException, InterruptedException {
        Stat s = zooKeeper.exists("/", false);
        System.out.println(s == null);
    }
    //CreateMode类型
    //PERSISTENT             持久型
    //PERSISTENT_SEQUENTIAL  持久顺序型
    //EPHEMERAL              临时型
    //EPHEMERAL_SEQUENTIAL   临时顺序型


    //ZooDefs.Ids.ACL类型
    //ANYONE_ID_UNSAFE  ID代表任何人
    //AUTH_IDS          可以设置ACL,代替了已经认证过客户端的IDs
    //OPEN_ACL_UNSAFE   表示完全开放的ACL，授于了除ADMIN以外的所有权限
    //CREATOR_ALL_ACL   创建znode的权限
    //READ_ACL_UNSAFE   给于world的能力去读数据

    @Test
    public void create() throws KeeperException, InterruptedException {
        String s = zooKeeper.create("/test", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(s);
    }
}
