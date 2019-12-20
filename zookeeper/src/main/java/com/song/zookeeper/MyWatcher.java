package com.song.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyWatcher implements Watcher {

    private ZooKeeper zooKeeper;
    private String rootNode;
    private String lockNode;
    private String waitNode;
    private String currentNode;
    private CountDownLatch countDownLatch;

    public MyWatcher(String rootNode, String lockNode) {
        this.rootNode = rootNode;
        this.lockNode = lockNode;
        conn();
    }

    //建立连接
    private void conn() {
        Stat stat;
        try {
            this.zooKeeper = new ZooKeeper("127.0.0.1:2181", 30000, this);
            stat = this.zooKeeper.exists(this.rootNode, false);
            if (stat == null) {
                this.zooKeeper.create(this.rootNode, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (InterruptedException | KeeperException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getLock() throws KeeperException, InterruptedException {

        if (tryLock()) {
            return true;
        } else {
            return waitLock();
        }

    }

    //创建节点，判断自己是否为最小节点，是则获得锁，不是则监听上一个节点
    private boolean tryLock() throws KeeperException, InterruptedException {
        currentNode = zooKeeper.create(rootNode + "/" + lockNode, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        currentNode = currentNode.split("/")[2];
        System.out.println(currentNode);
        List<String> nodes = zooKeeper.getChildren(rootNode, false);
        Collections.sort(nodes);
        if (currentNode.equals(nodes.get(0))) {
            return true;
        }
        waitNode = nodes.get(nodes.indexOf(currentNode) - 1);
        return false;
    }

    //使用计数器等待节点锁释放
    private boolean waitLock() {
        boolean lock = false;
        try {
            Stat stat = zooKeeper.exists(rootNode + "/" + waitNode, true);
            if (null != stat) {
                this.countDownLatch = new CountDownLatch(1);
                lock = countDownLatch.await(20000, TimeUnit.MILLISECONDS);
                countDownLatch = null;
            } else {
                lock = true;
            }
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
        return lock;
    }

    public void unlock() throws InterruptedException {
        try {
            Stat stat = zooKeeper.exists("/" + currentNode, false);
            if (null != stat) {
                zooKeeper.delete(currentNode, -1);
                currentNode = null;

            }
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        } finally {
            zooKeeper.close();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if (countDownLatch != null && watchedEvent.getType() == Event.EventType.NodeDeleted) {
                List<String> nodes = zooKeeper.getChildren(rootNode, false);
                Collections.sort(nodes);
                if (currentNode.equals(nodes.get(0))) {
                    countDownLatch.countDown();
                } else {
                    waitNode = nodes.get(nodes.indexOf(currentNode) - 1);
                    Stat stat = zooKeeper.exists(rootNode + "/" + waitNode, true);
                    if (null == stat) {
                        countDownLatch.countDown();
                    }
                }
            }
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
