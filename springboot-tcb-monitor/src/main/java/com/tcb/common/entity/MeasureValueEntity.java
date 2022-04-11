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
 * 记录测点处的测量数据。
 *
 * PS: 测量数据与采集数据不同，采集数据只记录着采集器的来源信息，而测量数据不仅记录着采集器来源信息，而且还记录着测点处的设备源的相关信息
 */
@Setter
@Getter
@Entity(name = "measure_value")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class MeasureValueEntity extends ValueEntity {
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
     * 设备编号
     */
    private String deviceNumber;

    /**
     * 测点编号
     */
    private String measurePointNumber;

    /**
     * 采集通道的采集数据
     */
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private ChannelValue value;

}
