package com.tcb.common.event;

import com.tcb.common.constant.MonitorEventType;
import com.tcb.common.model.Device;
import com.tcb.common.model.MeasurePoint;
import com.tcb.common.model.value.MeasureValue;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuhai
 * 测点数据产生事件
 */
@Getter
@Setter
public class MeasureValueEvent extends SpringApplicationEvent {

    /**
     * 测量点的测量数据
     */
    private MeasureValue measureValue;

    public MeasureValueEvent(MeasurePoint measurePoint, MeasureValue measureValue) {
        super(measurePoint, MonitorEventType.CHANNEL_FAULT_EVENT);
        this.measureValue = measureValue;
    }

    @Override
    public String getSourceId() {
        MeasurePoint measurePoint = (MeasurePoint) getSource();
        return measurePoint.getId();
    }

    @Override
    public String getSourceName() {
        MeasurePoint measurePoint = (MeasurePoint) getSource();
        Device device = measurePoint.getDevice();
        return String.format("设备: %s, 测点: %s", device.getNumber(), measurePoint.getNumber());
    }
}
