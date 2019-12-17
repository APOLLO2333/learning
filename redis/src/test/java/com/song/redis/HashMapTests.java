package com.song.redis;

import java.util.concurrent.ConcurrentHashMap;

public class HashMapTests {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        ATheard aTheard = new ATheard(map);
        BTheard bTheard = new BTheard(map);
        map.put("song", "mainThread");
        new Thread(aTheard).start();
        new Thread(bTheard).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("song"));
    }
}
