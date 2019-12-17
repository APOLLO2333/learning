package com.song.mybatis;


import com.song.mybatis.entity.Attrs;
import com.song.mybatis.entity.PersonAttrs;
import com.song.mybatis.entity.PersonParent;
import com.song.mybatis.service.IPersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTests {

    @Autowired
    private IPersonService personService;

    @Test
    public void get(){
        PersonAttrs personAttrs = personService.get(1);
        System.out.println(personAttrs.getAttrs().size());
        List<Attrs> list = personAttrs.getAttrs();
        System.out.println(1);
    }

    @Test
    public void getParent(){
        PersonParent personParent = personService.getParent(1);
        System.out.println(personParent.getParent().getName());
    }
}
