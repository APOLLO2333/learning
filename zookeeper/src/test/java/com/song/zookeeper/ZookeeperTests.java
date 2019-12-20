package com.song.zookeeper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperTests {

    @Autowired
    private TaskExecutor taskExecutor;


    @Test
    public void test() {
        Person person = new Person(0);
        String root = "/zTest";
        String lockNode = "locked";
        for (int i = 0; i < 20; i++) {
            taskExecutor.execute(new AgeAdd(person, new MyWatcher(root, lockNode)));
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(person.getAge());
    }
}
