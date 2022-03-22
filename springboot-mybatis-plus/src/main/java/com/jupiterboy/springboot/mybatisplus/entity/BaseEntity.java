package com.jupiterboy.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author yuhai
 * 公共的实体类的基类
 */
@Setter
@Getter
public class BaseEntity implements java.io.Serializable{

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * 创建用户
     */
    private String createUser = "system";

}
