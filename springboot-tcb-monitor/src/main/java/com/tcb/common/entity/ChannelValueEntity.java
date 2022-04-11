package com.tcb.common.entity;

import com.tcb.common.model.value.ChannelValue;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author yuhai
 *
 * 记录通道的采集数据。
 *
 * PS: 记录采集器通道的采集数据
 */
@Setter
@Getter
@Entity(name = "channel_value")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ChannelValueEntity extends ValueEntity {
    /**
     * 采集器编号
     */
    private String collectorNumber;

    /**
     * 采集器编号
     */
    private String channelNumber;

    /**
     * 采集器编号
     */
    private Integer channelType;

    /**
     * 采集通道的采集数据
     */
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private ChannelValue value;



}
