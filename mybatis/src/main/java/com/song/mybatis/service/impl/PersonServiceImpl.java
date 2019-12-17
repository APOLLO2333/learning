package com.song.mybatis.service.impl;

import com.song.mybatis.entity.Person;
import com.song.mybatis.entity.PersonAttrs;
import com.song.mybatis.entity.PersonParent;
import com.song.mybatis.mapper.PersonMapper;
import com.song.mybatis.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author supersong
 * @since 2019-11-29
 */
@Service
@Transactional
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public PersonAttrs get(Integer id) {
        return personMapper.getPersonAttsById(id);
    }

    @Override
    public PersonParent getParent(Integer id) {
        return personMapper.getPersonParentById(id);
    }
}
