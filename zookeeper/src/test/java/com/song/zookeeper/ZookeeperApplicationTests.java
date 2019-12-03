package com.song.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
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

    ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper("120.78.149.155:8040", 2000, watchedEvent -> {

        });
    }

    @Test
    public void exists() throws KeeperException, InterruptedException {
        Stat s = zooKeeper.exists("/",false);
        System.out.println(s == null);
    }
}
