package com.song.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuratorTests {

    private CuratorFramework client;

    @Autowired
    private TaskExecutor taskExecutor;

    @Before
    public void init() {
        client = CuratorFrameworkFactory.newClient("120.78.149.155:8040", new ExponentialBackoffRetry(3000, 3, Integer.MAX_VALUE));
        client.start();
    }

    @Test
    public void curatorTest() throws InterruptedException {
        Person p = new Person(0);
        for (int i = 0; i < 50; i++) {
            InterProcessMutex lock = new InterProcessMutex(client, "/curatorLock");
            AgeAddCurator ageAddCurator = new AgeAddCurator(lock, p);
            taskExecutor.execute(ageAddCurator);
        }
        Thread.sleep(3000);
        System.out.println(p.getAge());
    }
}
