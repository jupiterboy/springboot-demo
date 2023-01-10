package com.jupiterboy.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jupiterboy.springboot.mybatisplus.parameter.CollectorParameter;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * 采集器
 */
@Setter
@Getter
public class CollectorEntity extends ConfigEntity<CollectorParameter> {

    /**
     * 采集所属组织机构
     */
    private String orgId;

    @TableField(exist = false)
    private Set<ChannelEntity> channelEntities = new HashSet<>();


}