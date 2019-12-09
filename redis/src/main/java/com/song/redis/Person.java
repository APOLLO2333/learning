package com.song.redis;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Person implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
