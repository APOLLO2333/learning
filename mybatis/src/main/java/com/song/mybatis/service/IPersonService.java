package com.song.mybatis.service;

import com.song.mybatis.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;
import com.song.mybatis.entity.PersonAttrs;
import com.song.mybatis.entity.PersonParent;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author supersong
 * @since 2019-11-29
 */
public interface IPersonService extends IService<Person> {
    PersonAttrs get(Integer id);

    PersonParent getParent(Integer id);
}
