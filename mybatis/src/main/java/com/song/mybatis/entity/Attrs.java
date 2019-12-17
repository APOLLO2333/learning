package com.song.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author supersong
 * @since 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Attrs implements Serializable {

    private static final long serialVersionUID=1L;

    private String attr;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String value;

    private Integer personId;


}
