package com.song.zookeeper;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

public class AgeAddCurator implements Runnable {

    private final InterProcessMutex lock;
    private Person person;


    public AgeAddCurator(InterProcessMutex lock, Person person) {
        this.lock = lock;
        this.person = person;
    }

    @Override
    public void run() {
        try {
            if (lock.acquire(30, TimeUnit.SECONDS)){
                person.setAge(person.getAge() + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
