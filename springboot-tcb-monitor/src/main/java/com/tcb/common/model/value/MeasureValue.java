package com.tcb.common.model.value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.entity.MeasureValueEntity;
import com.tcb.common.model.MeasurePoint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yuhai
 * 测点值存储采集通道的采集的数值，同时存储着采集器测的相关信息和设备测的相关信息
 * @see MeasureValueEntity
 */
@Getter
@Setter
@NoArgsConstructor
public class MeasureValue extends ChannelValue implements Serializable {

    @JsonIgnore
    private MeasurePoint measurePoint;

    /**
     * 记录测点值的存储id
     */
    private Long id;

    public MeasureValueEntity createMeasureValueEntity(){
        MeasureValueEntity entity = new MeasureValueEntity();
        entity.setCollectorNumber(measurePoint.getChannel().getCollector().getNumber());
        entity.setChannelNumber(measurePoint.getChannel().getNumber());
        entity.setChannelType(measurePoint.getChannel().getType());
        entity.setDeviceNumber(measurePoint.getDevice().getNumber());
        entity.setMeasurePointNumber(measurePoint.getNumber());
        entity.setValue(this);
        entity.setCreateTime(getTimestamp());
        return entity;
    }

}
