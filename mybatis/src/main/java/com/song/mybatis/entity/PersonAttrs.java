package com.song.mybatis.entity;

import lombok.Data;

import java.util.List;

@Data

public class PersonAttrs extends Person{
    private List<Attrs> attrs;
}
