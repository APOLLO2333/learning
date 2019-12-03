package com.song.mybatis.mapper;

import com.song.mybatis.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author supersong
 * @since 2019-11-29
 */
@CacheNamespace
public interface PersonMapper extends BaseMapper<Person> {

}
