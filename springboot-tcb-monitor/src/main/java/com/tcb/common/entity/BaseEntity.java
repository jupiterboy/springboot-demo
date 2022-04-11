package com.tcb.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author yuhai
 * 公共的实体类
 */
@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity<ID> implements java.io.Serializable{

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = new Date();

    /**
     * 创建用户
     */
    private String createUser = "system";

    public abstract ID getId();

}
