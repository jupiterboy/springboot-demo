package com.jupiterboy.springboot.mybatisplus.entity;

import com.jupiterboy.springboot.mybatisplus.parameter.ChannelParameter;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuhai
 * 采集通道
 */
@Setter
@Getter
public class ChannelEntity extends ConfigEntity<ChannelParameter> {

    /**
     *  通道所属采集器
     */
    private String collectorId;

}
