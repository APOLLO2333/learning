package com.song.redis;

import java.util.concurrent.ConcurrentHashMap;

public class ATheard implements Runnable {

    private final ConcurrentHashMap<String, String> map;

    public ATheard(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        synchronized (this.map) {
            System.out.println("线程A获取的值：" + this.map.get("song"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程A获取的值：" + this.map.get("song"));
        }


    }
}
