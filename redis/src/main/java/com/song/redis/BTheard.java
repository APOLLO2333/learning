package com.song.redis;

import java.util.concurrent.ConcurrentHashMap;

public class BTheard implements Runnable {


    private final ConcurrentHashMap<String, String> map;

    public BTheard(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        synchronized (this.map) {
            System.out.println("线程B获取的值：" + this.map.get("song"));
            this.map.put("song", "BTheard");
        }

    }
}
