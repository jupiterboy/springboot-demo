package com.tcb.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.ChannelFault;
import com.tcb.common.constant.ChannelStatus;
import com.tcb.common.model.parameter.ChannelParameter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author yuhai
 * 采集通道
 */
@Setter
@Getter
@Entity(name="channel")
public class ChannelEntity extends ConfigEntity<ChannelParameter> {

    /**
     *  通道所属采集器
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "collector_id")
    private CollectorEntity collectorEntity;

    /**
     *  通道所属模块
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id")
    private ModuleEntity moduleEntity;

    /**
     * 采集通道关联的设备侧点
     * PS:一个采集通道关联一个采集测点
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="monitor_point_id")
    private MeasurePointEntity measurePointEntity;

    /**
     * 采集通道工作状态，0：正常，1：故障
     *
     * @see ChannelStatus
     */
    private Integer status = ChannelStatus.NORMAL;

    /**
     * 采集通道故障类型,该字段在status为ChannelStatus.FAULT时有效
     */
    private Integer fault = ChannelFault.NONE;

}
