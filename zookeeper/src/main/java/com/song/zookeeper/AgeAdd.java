package com.song.zookeeper;

import org.apache.zookeeper.KeeperException;

public class AgeAdd implements Runnable {

    private Person person;

    private MyWatcher myWatcher;

    public AgeAdd(Person person, MyWatcher myWatcher) {
        this.person = person;
        this.myWatcher = myWatcher;
    }

    @Override
    public void run() {
        try {
            if (myWatcher.getLock()) {
                person.setAge(person.getAge() + 1);
                myWatcher.unlock();
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
