package com.tcb.common.model.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tcb.common.constant.ChannelType;
import com.tcb.common.entity.ChannelValueEntity;
import com.tcb.common.model.Channel;
import com.tcb.common.model.MeasurePoint;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuhai
 *
 * 统一的采集器通道数据封装, 数据都通过json格式保存到数据库字段中,具体的数据类型可以根据字段类型进行获取
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelValue implements Serializable {

    @JsonIgnore
    private Channel channel;

    private int type = ChannelType.UNKNOWN;

    @JsonIgnore
    private boolean fault;

    @JsonIgnore
    private int faultType;

    // TODO: 根据项目创建不同的通道值
    /**
     * 振动通道值
     */
    private VibrationValue vibrationValue;

    /**
     * 温度通道值
     */
    private TemperatureValue temperatureValue;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp = new Date();

    public MeasureValue toMeasureValue(MeasurePoint measurePoint) {
        MeasureValue measureValue = new MeasureValue();
        measureValue.setMeasurePoint(measurePoint);
        BeanUtils.copyProperties(this, measureValue);
        return measureValue;
    }

    public ChannelValueEntity createEntity(){
        ChannelValueEntity entity = new ChannelValueEntity();
        entity.setCollectorNumber(channel.getCollector().getNumber());
        entity.setChannelNumber(channel.getNumber());
        entity.setChannelType(channel.getType());
        entity.setValue(this);
        return entity;
    }

    /**
     * @author yuhai
     *
     * 温度通道采集值
     */
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TemperatureValue implements ChannelField {

        // TODO: 根据项目创建不同的温度字段

        // 测试样例属性
        private double value1;
        private double value2;


        @Override
        public ChannelValue toChannelValue(Channel channel) {
            return ChannelValue.builder().temperatureValue(this).channel(channel).type(channel.getType()).build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VibrationValue implements ChannelField {

        // TODO: 根据项目创建不同的振动字段

        // 测试样例属性
        private double value1;
        private double value2;

        @Override
        public ChannelValue toChannelValue(Channel channel) {
            return ChannelValue.builder().vibrationValue(this).channel(channel).type(channel.getType()).build();
        }
    }

}
