package com.song.mybatis.entity;

import lombok.Data;

@Data
public class PersonParent extends Person {
    private Parent parent;
}
