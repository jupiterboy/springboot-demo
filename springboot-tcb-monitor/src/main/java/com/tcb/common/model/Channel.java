package com.tcb.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.ChannelFault;
import com.tcb.common.constant.ChannelStatus;
import com.tcb.common.entity.ChannelEntity;
import com.tcb.common.model.generator.RandomChannelValueGenerator;
import com.tcb.common.model.parameter.ChannelParameter;
import com.tcb.common.model.value.ChannelValue;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author yuhai
 *
 * 采集器通道模型，映射实际的采集通道
 *
 */
@Getter
@Setter
public class Channel extends ConfigModel<ChannelEntity, ChannelParameter>{

    /**
     * 通道所属采集器
     */
    @JsonIgnore
    private Collector collector;

    /**
     * 通道所属的模块
     */
    @JsonIgnore
    private CollectorModule collectorModule;

    /**
     * 与通道关联的测点
     */
    private MeasurePoint measurePoint;

    /**
     * 缓存最近的通道数据
     */
    private ChannelValue channelValue;

    /**
     * 通道消息计数
     */
    private long receiveCount;

    /**
     * 最新接收消息时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date latestReceiveTime;

    public Channel(Collector collector, ChannelEntity entity) {
        super(entity);
        this.collector = collector;

        // 从缓存初始化时，entity直接没有自动进行关联，需要手动关联
        entity.setCollectorEntity(collector.getEntity());
    }

    /**
     * 获取通道工作状态
     */
    public Integer getStatus() {
        return entity.getStatus();
    }

    /**
     * 设置通道工作状态
     * @param status    通道工作状态
     * @see ChannelStatus
     */
    public void setStatus(Integer status) {
        entity.setStatus(status);
    }

    /**
     * 获取通道的故障类型，当通道故障时，返回故障类型
     */
    public Integer getFault() {
        return entity.getFault();
    }

    /**
     * 设置通道的故障类型
     * @param fault   通道的故障类型
     * @see ChannelFault
     */
    public void setFault(Integer fault) {
        entity.setFault(fault);
    }

    /**
     * 随机生成通道值
     * @return 随机生成的通道值
     */
    public ChannelValue sample() {
        return new RandomChannelValueGenerator().generate(this);
    }
}
