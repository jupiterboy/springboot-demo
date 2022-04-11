package com.tcb.common.model.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.entity.AlarmEntity;
import com.tcb.common.entity.MonitorValueEntity;
import com.tcb.common.model.MonitorItem;
import lombok.*;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitorValue implements Serializable {

    @JsonIgnore
    private MonitorItem monitorItem;

    @JsonIgnore
    private MeasureValue measureValue;

    private int type;

    private Double value;

    private Integer status = AlarmStatus.NORMAL;

    @JsonIgnore
    private String message;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp = new Date();

    public MonitorValueEntity createMonitorValueEntity(){
        Assert.notNull(monitorItem, "monitorItem must not be null");
        MonitorValueEntity entity = new MonitorValueEntity();
        entity.setDeviceNumber(monitorItem.getMeasurePoint().getDevice().getNumber());
        entity.setMeasurePointNumber(monitorItem.getMeasurePoint().getNumber());
        entity.setMonitorItemType(monitorItem.getType());
        entity.setValue(value);
        entity.setStatus(status);
        if (measureValue != null && measureValue.getId() != null) {
            entity.setMeasureValueId(measureValue.getId());
        }
        return entity;
    }

    public AlarmEntity createAlarmEntity() {
        Assert.notNull(monitorItem, "monitorItem must not be null");
        AlarmEntity entity = new AlarmEntity();
        entity.setDeviceNumber(monitorItem.getMeasurePoint().getDevice().getNumber());
        entity.setMeasurePointNumber(monitorItem.getMeasurePoint().getNumber());
        entity.setMonitorItemType(monitorItem.getType());
        entity.setStatus(status);
        entity.setValue(value);
        entity.setMessage(message);
        entity.setParameter(monitorItem.getParameter());
        return entity;
    }

    @JsonIgnore
    public boolean isNormal() {
        return status == AlarmStatus.NORMAL;
    }

    /**
     * 检查监测值状态
     */
    public void checkStatus(){
        status = monitorItem.checkStatus(value);
    }

}
