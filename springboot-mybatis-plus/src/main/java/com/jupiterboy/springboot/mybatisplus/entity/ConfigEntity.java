package com.jupiterboy.springboot.mybatisplus.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author yuhai
 *
 * 配置相关实体类的基类
 *
 * 实体关系参见resource/banner.txt
 */
@Setter
@Getter
public class ConfigEntity<P> extends BaseEntity {

    /**
     * 配置实体名称
     */
    private String name;

    /**
     * 配置实体类型
     */
    private int type;

    /**
     * 配置实体编号
     */
    private String number;

    /**
     * 配置实体描述
     */
    private String description;

    /**
     * 配置实体更新时间
     */
    private Date updateTime;

    /**
     * 配置实体更新用户
     */
    private String updateUser;

    /**
     * 配置实体参数
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private P parameter;
}
