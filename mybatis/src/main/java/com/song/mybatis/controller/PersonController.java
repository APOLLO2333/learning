package com.song.mybatis.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author supersong
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/person")
public class PersonController {


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}

