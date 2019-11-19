package com.song.client;

public class Back implements ClientOneFeign {
    @Override
    public String hello() {
        return "back success";
    }
}
